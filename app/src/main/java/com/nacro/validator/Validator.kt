package com.nacro.validator

import com.nacro.validator.case.ErrorAction

fun <T> check(content: T, condition: T.() -> List<String>): Result<Unit> {
    val errors = condition(content)
    return if (errors.isEmpty()) {
        Result.Success(Unit)
    } else {
        Result.Error(CheckException(errors))
    }
}

fun constraint(checkAll: Boolean, conditions: Array<ErrorAction>): List<String> {
    val out = arrayListOf<String>()
    for (c in conditions) {
        c()?.let {
            out.add(it)
            if (!checkAll) return out
        }
    }
    return out
}


infix fun (Boolean).error(msg: String): ErrorAction {
    return { if (this) msg else null }
}
