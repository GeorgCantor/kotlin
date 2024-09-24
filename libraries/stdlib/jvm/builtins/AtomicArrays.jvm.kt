/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
@file:Suppress("NEWER_VERSION_IN_SINCE_KOTLIN")
package kotlin.concurrent

/**
 * An array of ints in which elements may be updated atomically.
 *
 * Instances of [AtomicIntArray] are represented by [java.util.concurrent.atomic.AtomicIntegerArray] and provide the the same atomicity guarantees.
 */
@SinceKotlin("2.1")
public actual class AtomicIntArray {
    /**
     * Creates a new [AtomicIntArray] of the given [size], with all elements initialized to zero.
     *
     * @throws RuntimeException if the specified [size] is negative.
     */
    public actual constructor(size: Int)

    /**
     * Creates a new [AtomicIntArray] filled with elements of the given [array].
     */
    public actual constructor(array: IntArray)

    /**
     * Returns the number of elements in the array.
     */
    public actual val size: Int get() = array.size

    /**
     * Atomically gets the value of the element at the given [index].
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun loadAt(index: Int): Int

    /**
     * Atomically sets the value of the element at the given [index] to the [new value][newValue].
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun storeAt(index: Int, newValue: Int)

    /**
     * Atomically sets the value of the element at the given [index] to the [new value][newValue]
     * and returns the old value of the element.
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun exchangeAt(index: Int, newValue: Int): Int

    /**
     * Atomically sets the value of the element at the given [index] to the [new value][newValue]
     * if the current value equals the [expected value][expectedValue].
     * Returns true if the operation was successful and false only if the current value of the element was not equal to the expected value.
     *
     * Comparison of values is done by value.
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun compareAndSetAt(index: Int, expectedValue: Int, newValue: Int): Boolean

    /**
     * Atomically sets the value of the element at the given [index] to the [new value][newValue]
     * if the current value equals the [expected value][expectedValue] and returns the old value of the element in any case.
     *
     * Comparison of values is done by value.
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun compareAndExchangeAt(index: Int, expectedValue: Int, newValue: Int): Int

    /**
     * Atomically adds the given [delta] to the element at the given [index] and returns the old value of the element.
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun fetchAndAddAt(index: Int, delta: Int): Int

    /**
     * Atomically adds the given [delta] to the element at the given [index] and returns the new value of the element.
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun addAndFetchAt(index: Int, delta: Int): Int

    /**
     * Returns the string representation of the underlying array of ints.
     *
     * This operation does not provide any atomicity guarantees.
     */
    public actual override fun toString(): String
}

/**
 * An array of longs in which elements may be updated atomically.
 *
 * Instances of [AtomicLongArray] are represented by [java.util.concurrent.atomic.AtomicLongArray] and provide the the same atomicity guarantees.
 */
@SinceKotlin("2.1")
public actual class AtomicLongArray {
    /**
     * Creates a new [AtomicLongArray] of the given [size], with all elements initialized to zero.
     *
     * @throws RuntimeException if the specified [size] is negative.
     */
    public actual constructor(size: Int)

    /**
     * Creates a new [AtomicLongArray] filled with elements of the given [array].
     */
    public actual constructor(array: LongArray)

    /**
     * Returns the number of elements in the array.
     */
    public actual val size: Int get() = array.size

    /**
     * Atomically gets the value of the element at the given [index].
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun loadAt(index: Int): Long

    /**
     * Atomically sets the value of the element at the given [index] to the [new value][newValue].
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun storeAt(index: Int, newValue: Long)

    /**
     * Atomically sets the value of the element at the given [index] to the [new value][newValue]
     * and returns the old value of the element.
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun exchangeAt(index: Int, newValue: Long): Long

    /**
     * Atomically sets the value of the element at the given [index] to the [new value][newValue]
     * if the current value equals the [expected value][expectedValue].
     * Returns true if the operation was successful and false only if the current value of the element was not equal to the expected value.
     *
     * Comparison of values is done by value.
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun compareAndSetAt(index: Int, expectedValue: Long, newValue: Long): Boolean

    /**
     * Atomically sets the value of the element at the given [index] to the [new value][newValue]
     * if the current value equals the [expected value][expectedValue] and returns the old value of the element in any case.
     *
     * Comparison of values is done by value.
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun compareAndExchangeAt(index: Int, expectedValue: Long, newValue: Long): Long

    /**
     * Atomically adds the given [delta] to the element at the given [index] and returns the old value of the element.
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun fetchAndAddAt(index: Int, delta: Long): Long

    /**
     * Atomically adds the given [delta] to the element at the given [index] and returns the new value of the element.
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun addAndFetchAt(index: Int, delta: Long): Long

    /**
     * Returns the string representation of the underlying array of longs.
     *
     * This operation does not provide any atomicity guarantees.
     */
    public actual override fun toString(): String
}

/**
 * A generic array of objects in which elements may be updated atomically.
 *
 * Instances of [AtomicArray] are represented by [java.util.concurrent.atomic.AtomicReferenceArray] and provide the the same atomicity guarantees.
 */
@SinceKotlin("2.1")
public actual class AtomicArray<T> {
    /**
     * Creates a new [AtomicArray]<T> filled with elements of the given [array].
     */
    public actual constructor (array: Array<T>)

    /**
     * Returns the number of elements in the array.
     */
    public actual val size: Int

    /**
     * Atomically gets the value of the element at the given [index].
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun loadAt(index: Int): T

    /**
     * Atomically sets the value of the element at the given [index] to the [new value][newValue].
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun storeAt(index: Int, newValue: T)

    /**
     * Atomically sets the value of the element at the given [index] to the [new value][newValue]
     * and returns the old value of the element.
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun exchangeAt(index: Int, newValue: T): T

    /**
     * Atomically sets the value of the element at the given [index] to the [new value][newValue]
     * if the current value equals the [expected value][expectedValue].
     * Returns true if the operation was successful and false only if the current value of the element was not equal to the expected value.
     *
     * Comparison of values is done by value.
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun compareAndSetAt(index: Int, expectedValue: T, newValue: T): Boolean

    /**
     * Atomically sets the value of the element at the given [index] to the [new value][newValue]
     * if the current value equals the [expected value][expectedValue] and returns the old value of the element in any case.
     *
     * Comparison of values is done by value.
     *
     * @throws [IndexOutOfBoundsException] if the [index] is out of bounds of this array.
     */
    public actual fun compareAndExchangeAt(index: Int, expectedValue: T, newValue: T): T

    /**
     * Returns the string representation of the underlying array of objects.
     *
     * This operation does not provide any atomicity guarantees.
     */
    public actual override fun toString(): String
}