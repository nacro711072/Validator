package com.nacro.validator

import kotlin.Exception

fun <T> check(content: T, condition: T.() -> List<String>): Result<Unit> {
    val errors = condition(content)
    return if (errors.isEmpty()) {
        Result.Success(Unit)
    } else {
        Result.Error(CheckException(errors))
    }
}

fun allCondition(vararg conditions: () -> String?): List<String> {
    val out = arrayListOf<String>()
    for (c in conditions) {
        c()?.let { out.add(it) }
    }
    return out
}

fun anyCondition(vararg conditions: () -> String?): List<String> {
    val out = arrayListOf<String>()
    for (c in conditions) {
        c()?.let {
            out.add(it)
            return out
        }
    }
    return out
}


infix fun (Boolean).error(msg: String): (() -> String?) {
    return {
        if (this) msg else null
    }
}
