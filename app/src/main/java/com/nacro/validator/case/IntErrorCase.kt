package com.nacro.validator.case

import com.nacro.validator.error

object IntScope {
    fun Int.validRange(range: IntRange): ErrorAction {
        return (this !in range) error "value must in $range"
    }

    fun Int.alertNonNegative(): ErrorAction {
        return (this < 0) error "value must NON negative"
    }

    fun Int.biggerThen(i: Int): ErrorAction {
        return (this > i) error "value must BIGGER then $i"
    }

    fun Int.smallThen(i: Int): ErrorAction {
        return (this < i) error "value must SMALLER then $i"
    }

}