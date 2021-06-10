package com.nacro.validator.case

import android.util.Patterns
import com.nacro.validator.error
import com.nacro.validator.pattern.email
import com.nacro.validator.pattern.letterAndDigit
import com.nacro.validator.pattern.phone

object StringScope {

    fun String.alertNonEmpty(): ErrorAction {
        return isEmpty() error "can't be empty"
    }

    fun String.validSize(range: IntProgression): ErrorAction {
        return (length !in range) error "length must in $range"
    }

    fun String.validEmailFormat(): ErrorAction {
        return matchRe(email, "email format invalid")
    }

    fun String.validPhoneFormat(): ErrorAction {
        return matchRe(phone, "phone number format invalid")
    }

    fun String.wordAndDigitOnly(): ErrorAction {
        return matchRe(letterAndDigit, "must have word and digit, exclude !@#$%... etc.")
    }


    fun String.matchRe(re: Regex, errorMsg: String): ErrorAction {
        return !matches(re) error errorMsg
    }

    fun String.sameWith(s: String): ErrorAction {
        return contentEquals(s) error "value: \"${this}\" not equal \"${s}\""
    }
}