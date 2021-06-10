package com.nacro.validator.test

import com.nacro.validator.*
import com.nacro.validator.case.IntScope.validRange
import com.nacro.validator.case.StringScope.alertNonEmpty
import com.nacro.validator.case.StringScope.matchRe
import com.nacro.validator.case.StringScope.validSize

fun main() {
    println("=========check string=========")
    testCase1("aaa")
    println("=========check int=========")
    testCase2(123)
}

fun testCase1(content: String) {
    val checkStr = check(content) {
        constraint(true,
            arrayOf(
                alertNonEmpty(),
                validSize(6..8),
                matchRe(Regex("\\d+"), "must has digit")
            )
        )
    }

    with(checkStr) {
        ifError { println(it.message) }
        ifSuccess { println("check string success") }
    }

}

fun testCase2(content: Int) {
    val checkInt = check(content) {
        constraint(false,
            arrayOf(
                validRange((100 until 1000)),
                (this % 2 == 0) error "the value must be odd"
            )
        )
    }

    with(checkInt) {
        ifError { println(it.message) }
        ifSuccess { println("check int success") }
    }

}