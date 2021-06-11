package com.nacro.validator.test

import com.nacro.validator.case.logic.LoginValidator

fun main() {
    val validator = LoginValidator()
    testAccount(validator)
    testPassword(validator)
}

fun testAccount(validator: LoginValidator) {

    val account = "wdwfe@asc.com"
    val invalidAccount = "wdwfe@1!!asc.com"

    println("===test account: $account ===")
    with(validator.accountChecker(account)) {
        ifError { println(it.message) }
        ifSuccess { println("validate success, account: $account") }
    }
    println("===test account: $invalidAccount ===")
    with(validator.accountChecker(invalidAccount)) {
        ifError { println(it.message) }
        ifSuccess { println("validate success, account: $invalidAccount") }
    }
}

fun testPassword(validator: LoginValidator) {

    val password = "nacro711092"
    val inValidPassword1 = "11092"
    val inValidPassword2 = "aa11092%^^"

    println("===test password: $password ===")
    with(validator.passwordChecker(password)) {
        ifError { println(it.message) }
        ifSuccess { println("validate success, password: $password") }
    }
    println("===test password: $inValidPassword1 ===")
    with(validator.passwordChecker(inValidPassword1)) {
        ifError { println(it.message) }
        ifSuccess { println("validate success, password: $inValidPassword1") }
    }

    println("===test password: $inValidPassword2 ===")
    with(validator.passwordChecker(inValidPassword2)) {
        ifError { println(it.message) }
        ifSuccess { println("validate success, password: $inValidPassword2") }
    }

}