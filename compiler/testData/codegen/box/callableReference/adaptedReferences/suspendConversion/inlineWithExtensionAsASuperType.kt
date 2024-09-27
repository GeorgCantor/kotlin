// LANGUAGE: +FunctionalTypeWithExtensionAsSupertype
// WITH_STDLIB
// WITH_COROUTINES
import helpers.*
import kotlin.coroutines.*

fun runSuspend(c: suspend () -> Unit) {
    c.startCoroutine(EmptyContinuation)
}

var test1 = "failed"
var test2 = "failed"

class A: Int.() -> Unit {
    override fun invoke(p1: Int) {
        test1 = "O"
    }
}

class B: (Int) -> Unit {
    override fun invoke(p1: Int) {
        test2 = "K"
    }
}

suspend inline fun invokeSuspend(fn: suspend Int.() -> Unit) { fn(1) }

fun box(): String {
    runSuspend {
        invokeSuspend((::A)())
        invokeSuspend((::B)())
    }
    return test1+test2
}