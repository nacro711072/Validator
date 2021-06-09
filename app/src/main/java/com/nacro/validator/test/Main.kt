package com.nacro.validator.test

import com.nacro.validator.*

fun main() {
    println("=========check string=========")
    testCase1("aaa")
    println("=========check int=========")
    testCase2(123)
}

fun testCase1(content: String) {
    val checkStr = check(content) {
        allCondition(
            isEmpty() error "can't be empty",
            (length !in 6..8) error "length must in [6, 8]",
            !contains(Regex("\\d+")) error "must has digit"
        )
    }

    with(checkStr) {
        ifError { println(it.message) }
        ifSuccess { println("check string success") }
    }

}

fun testCase2(content: Int) {
    val checkInt = check(content) {
        anyCondition(
            (this !in (100 until 1000)) error "the value must in [100. 1000)",
            (this % 2 == 0) error "the value must be odd"
        )
    }

    with(checkInt) {
        ifError { println(it.message) }
        ifSuccess { println("check int success") }
    }

}