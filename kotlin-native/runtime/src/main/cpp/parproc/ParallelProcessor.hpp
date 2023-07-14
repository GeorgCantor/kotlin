/*
 * Copyright 2010-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#pragma once

#include "../CompilerConstants.hpp"
#include "../KAssert.h"
#include "../Logging.hpp"
#include "../Utils.hpp"
#include "Porting.h"
#include "PushOnlyAtomicArray.hpp"
#include "SplitSharedList.hpp"
#include "BoundedQueue.hpp"

namespace kotlin {

/**
 * Coordinates a group of workers working in parallel on a large amounts of identical tasks.
 * The dispatcher will try to balance the work among the workers.
 *
 * Requirements:
 * -  Every instantiated worker must execute `tryPop` sooner or later;
 * -  Every instantiated worker must finish execution before the destruction of the processor;
 */
template <typename ListImpl, std::size_t kBatchSize, std::size_t kBatchesPoolSize>
class ParallelProcessor : private Pinned {
    class Batch {
    public:
        ALWAYS_INLINE bool empty() const noexcept {
            return elems_.empty();
        }

        ALWAYS_INLINE bool full() const noexcept {
            return elemsCount_ == kBatchSize;
        }

        ALWAYS_INLINE std::size_t elementsCount() const noexcept {
            return elemsCount_;
        }

        ALWAYS_INLINE bool tryPush(typename ListImpl::reference value) noexcept {
            RuntimeAssert(!full(), "Batch overflow");
            bool pushed = elems_.try_push_front(value);
            if (pushed) {
                ++elemsCount_;
            }
            return pushed;
        }

        ALWAYS_INLINE typename ListImpl::pointer tryPop() noexcept {
            auto popped = elems_.try_pop_front();
            if (popped) {
                --elemsCount_;
            }
            return popped;
        }

        void transferAllInto(ListImpl& dst) noexcept {
            dst.splice_after(dst.before_begin(), elems_.before_begin(), elems_.end(), std::numeric_limits<typename ListImpl::size_type>::max());
            RuntimeAssert(empty(), "All the elements must be transferred");
            elemsCount_ = 0;
        }

        void fillFrom(ListImpl& src) noexcept {
            auto spliced = elems_.splice_after(elems_.before_begin(), src.before_begin(), src.end(), kBatchSize);
            elemsCount_ = spliced;
        }

    private:
        ListImpl elems_;
        std::size_t elemsCount_ = 0;
    };

public:
    class WorkSource : private Pinned {
        friend ParallelProcessor;
    public:
        explicit WorkSource(ParallelProcessor& dispatcher) : dispatcher_(dispatcher), isWorker_(false) {}
        explicit WorkSource(ParallelProcessor& dispatcher, bool isWorker) : dispatcher_(dispatcher), isWorker_(isWorker) {}

        ALWAYS_INLINE bool localEmpty() const noexcept {
            return batch_.empty() && overflowList_.empty();
        }

        ALWAYS_INLINE bool tryPushLocal(typename ListImpl::reference value) noexcept {
            return overflowList_.try_push_front(value);
        }

        ALWAYS_INLINE typename ListImpl::pointer tryPopLocal() noexcept {
            return overflowList_.try_pop_front();
        }

        ALWAYS_INLINE bool tryPush(typename ListImpl::reference value) noexcept {
            if (batch_.full()) {
                bool released = dispatcher_.releaseBatch(std::move(batch_));
                if (!released) {
                    RuntimeLogDebug({ "balancing" }, "Batches pool overflow");
                    batch_.transferAllInto(overflowList_);
                }
                batch_ = Batch{};
            }
            return batch_.tryPush(value);
        }

        ALWAYS_INLINE typename ListImpl::pointer tryPop() noexcept {
            if (batch_.empty()) {
                while (true) {
                    bool acquired = dispatcher_.acquireBatch(batch_);
                    if (!acquired) {
                        if (!overflowList_.empty()) {
                            batch_.fillFrom(overflowList_);
                            RuntimeLogDebug({ "balancing" }, "Acquired %zu elements from the overflow list", batch_.elementsCount());
                        } else {
                            bool newWorkAvailable = waitForMoreWork();
                            if (newWorkAvailable) continue;
                            return nullptr;
                        }
                    }
                    RuntimeAssert(!batch_.empty(), "Must have acquired some elements");
                    break;
                }
            }

            return batch_.tryPop();
        }

        ALWAYS_INLINE bool forceFlush() noexcept {
            // FIXME simplify?
            while (true) {
                if (!batch_.empty()) {
                    bool released = dispatcher_.releaseBatch(std::move(batch_));
                    if (released) {
                        RuntimeLogDebug({ "balancing" }, "Work queue force flushed");
                        batch_ = Batch{};
                    } else {
                        RuntimeLogDebug({ "balancing" }, "Failed to force flush work queue");
                        return false;
                    };
                }
                RuntimeAssert(batch_.empty(), "Now must be empty");
                if (overflowList_.empty()) {
                    RuntimeAssert(localEmpty(), "Now local is empty");
                    return true;
                } else {
                    RuntimeLogDebug({ "balancing" }, "Refiling batch from overflow list");
                    batch_.fillFrom(overflowList_);
                }
            }
        }

    // FIXME
    protected:
        bool waitForMoreWork() noexcept {
            RuntimeAssert(isWorker_, "Must be a worker");
            RuntimeAssert(batch_.empty(), "Local batch must be depleted before waiting for shared work");
            RuntimeAssert(overflowList_.empty(), "Local overflow list must be depleted before waiting for shared work");

            std::unique_lock lock(dispatcher_.waitMutex_);

            auto nowWaiting = dispatcher_.waitingWorkers_.fetch_add(1, std::memory_order_relaxed) + 1;
            RuntimeLogDebug({ "balancing" }, "Worker goes to sleep (now sleeping %zu of %zu)",
                            nowWaiting, dispatcher_.registeredWorkers_.load(std::memory_order_relaxed));

            if (dispatcher_.allDone_) {
                dispatcher_.waitingWorkers_.fetch_sub(1, std::memory_order_relaxed);
                return false;
            }

            if (nowWaiting == dispatcher_.registeredWorkers_.load(std::memory_order_relaxed)) {
                // we are the last ones awake
                RuntimeLogDebug({ "balancing" }, "Worker has detected termination");
                dispatcher_.allDone_ = true;
                dispatcher_.waitingWorkers_.fetch_sub(1, std::memory_order_relaxed);
                lock.unlock();
                dispatcher_.waitCV_.notify_all();
                return false;
            }

            dispatcher_.waitCV_.wait(lock);
            dispatcher_.waitingWorkers_.fetch_sub(1, std::memory_order_relaxed);
            if (dispatcher_.allDone_) {
                return false;
            }
            RuntimeLogDebug({ "balancing" }, "Worker woke up");

            return true;
        }

        ParallelProcessor& dispatcher_;
        const bool isWorker_; // FIXME

        Batch batch_;
        ListImpl overflowList_;
    };

    class Worker : public WorkSource {
        friend ParallelProcessor;
    public:
        explicit Worker(ParallelProcessor& dispatcher) : WorkSource(dispatcher, true) {
            RuntimeAssert(this->isWorker_, "Must be a worker");
            this->dispatcher_.registeredWorkers_.fetch_add(1, std::memory_order_relaxed);
            RuntimeLogDebug({ "balancing" }, "Worker registered");
        }

    private:
    };

    ParallelProcessor() = default;

    ~ParallelProcessor() {
        RuntimeAssert(waitingWorkers_.load() == 0, "All the workers must terminate before dispatcher destruction");
    }

    size_t registeredWorkers() {
        return registeredWorkers_.load(std::memory_order_relaxed);
    }

    bool workAvailable() const noexcept {
        return sharedBatches_.size() > 0;
    }

    void undo() noexcept {
        // FIXME encapsulate
        std::unique_lock lock(waitMutex_);
        allDone_ = false;
    }

    void stop() noexcept {
        {
            std::unique_lock lock(waitMutex_);
            allDone_ = true;
        }
        waitCV_.notify_all();
    }

private:
    bool releaseBatch(Batch&& batch) {
        RuntimeAssert(!batch.empty(), "A batch to release into shared pool must be non-empty");
        RuntimeLogDebug({ "balancing" }, "Releasing batch of %zu elements", batch.elementsCount());
        bool shared = sharedBatches_.enqueue(std::move(batch));
        if (shared) {
            if (waitingWorkers_.load(std::memory_order_relaxed) > 0) {
                waitCV_.notify_one();
            }
        }
        return shared;
    }

    bool acquireBatch(Batch& dst) {
        RuntimeAssert(dst.empty(), "Destination batch must be already depleted");
        bool acquired = sharedBatches_.dequeue(dst);
        if (acquired) {
            RuntimeLogDebug({ "balancing" }, "Acquired a batch of %zu elements", dst.elementsCount());
        }
        return acquired;
    }

    BoundedQueue<Batch, kBatchesPoolSize> sharedBatches_;

    std::atomic<size_t> registeredWorkers_ = 0;
    std::atomic<size_t> waitingWorkers_ = 0;

    std::atomic<bool> allDone_ = false;
    mutable std::mutex waitMutex_;
    mutable std::condition_variable waitCV_;
};

}
