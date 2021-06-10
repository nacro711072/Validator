package com.nacro.validator.case

import android.util.Patterns
import com.nacro.validator.error

object StringScope {

    fun String.alertNonEmpty(): ErrorAction {
        return isEmpty() error "can't be empty"
    }

    fun String.validSize(range: IntProgression): ErrorAction {
        return (length !in range) error "length must in $range"
    }

    fun String.validEmailFormat(): ErrorAction {
        return !contains(Patterns.EMAIL_ADDRESS.toRegex()) error "email format invalid"
    }

    fun String.validPhoneFormat(): ErrorAction {
        return !contains(Patterns.PHONE.toRegex()) error "phone number format invalid"
    }

    fun String.matchRe(re: Regex, errorMsg: String): ErrorAction {
        return !contains(re) error errorMsg
    }

    fun String.sameWith(s: String): ErrorAction {
        return contentEquals(s) error "value: \"${this}\" not equal \"${s}\""
    }
}