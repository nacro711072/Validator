package com.nacro.validator.pattern

val email = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
        "\\@" +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(" +
        "\\." +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
        ")+").toRegex()

val letterAndDigit = "^([a-zA-Z]+\\d+)|(\\d+[a-zA-Z]+)[0-9a-zA-Z]*\$".toRegex()

val phone = ("(\\+[0-9]+[\\- \\.]*)?" // +<digits><sdd>*
            + "(\\([0-9]+\\)[\\- \\.]*)?" // (<digits>)<sdd>*
            + "([0-9][0-9\\- \\.]+[0-9])").toRegex() // <digit><digit|sdd>+<digit>
