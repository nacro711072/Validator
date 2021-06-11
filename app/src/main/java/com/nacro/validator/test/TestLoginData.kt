package com.nacro.validator.test

import com.nacro.validator.case.logic.LoginValidator
import com.nacro.validator.injection.DataValidatorInjection

class TestLoginData {
    fun testLoginData1(validator: LoginValidator) {
        val data = LoginData("wdwfe@asc.com", "711092")
        val results = DataValidatorInjection.inject(data, validator)
        results.forEach { entry ->
            println("name: ${entry.key}, error msg: [\n${entry.value.message}\n]")
        }
    }

    fun testLoginData2(validator: LoginValidator) {
        val data = LoginData("wdwfe111", "")
        val results = DataValidatorInjection.inject(data, validator)
        results.forEach { entry ->
            println("name: ${entry.key}, error msg: [\n${entry.value.message}\n]")
        }
    }
}

fun main() {
    val validator = LoginValidator()
    val tester = TestLoginData()
    println("=====start testLoginData1=====")
    tester.testLoginData1(validator)
    println("=====start testLoginData2=====")
    tester.testLoginData2(validator)
}

data class LoginData(val account: String, val password: String)