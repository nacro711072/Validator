package com.nacro.validator

class CheckException(val errors: List<String>): Exception(errors.joinToString(separator = "\n"))