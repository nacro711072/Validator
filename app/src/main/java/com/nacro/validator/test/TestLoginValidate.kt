package com.nacro.validator.test

import com.nacro.validator.case.logic.LoginValidator

fun main() {
    testAccount()
    testPassword()
}

fun testAccount() {
    val validator = LoginValidator()

    val account = "wdwfe@asc.com"
    val invalidAccount = "wdwfe@1!!asc.com"

    println("===test account: $account ===")
    with(validator.account(account)) {
        ifError { println(it.message) }
        ifSuccess { println("validate success, account: $account") }
    }
    println("===test account: $invalidAccount ===")
    with(validator.account(invalidAccount)) {
        ifError { println(it.message) }
        ifSuccess { println("validate success, account: $invalidAccount") }
    }
}

fun testPassword(validator: LoginValidator) {

    val password = "nacro711092"
    val inValidPassword1 = "11092"
    val inValidPassword2 = "aa11092%^^"

    println("===test password: $password ===")
    with(validator.password(password)) {
        ifError { println(it.message) }
        ifSuccess { println("validate success, password: $password") }
    }
    println("===test password: $inValidPassword1 ===")
    with(validator.password(inValidPassword1)) {
        ifError { println(it.message) }
        ifSuccess { println("validate success, password: $inValidPassword1") }
    }

    println("===test password: $inValidPassword2 ===")
    with(validator.password(inValidPassword2)) {
        ifError { println(it.message) }
        ifSuccess { println("validate success, password: $inValidPassword2") }
    }

}