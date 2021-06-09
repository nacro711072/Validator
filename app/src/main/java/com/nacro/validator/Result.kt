package com.nacro.validator

sealed class Result<out T> {
    class Success<out T>(val value: T): Result<T>()
    class Error(val e: Exception): Result<Nothing>()

    fun ifSuccess(block: (result: T) -> Unit) {
        if (this is Success) {
            block.invoke(this.value)
        }
    }

    fun ifError(block: (e: Exception) -> Unit) {
        if (this is Error) {
            block.invoke(this.e)
        }
    }
}
