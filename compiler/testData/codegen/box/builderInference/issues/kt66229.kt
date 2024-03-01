// WITH_STDLIB
// ISSUE: KT-66229
// IGNORE_BACKEND_K1: ANY
// Reason: red code

fun foo() {
    buildMap {
        for (v in this) {
            put(1, 1)
        }
    }
}

fun bar() {
    buildMap {
        mapValues { (key: Int, value: String) -> "1" }
    }
}

fun box(): String {
    foo()
    bar()
    return "OK"
}
