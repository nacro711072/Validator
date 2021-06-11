package com.nacro.validator.case.logic

import com.nacro.validator.case.StringScope
import com.nacro.validator.case.StringScope.alertNonEmpty
import com.nacro.validator.case.StringScope.matchRe
import com.nacro.validator.case.StringScope.validSize
import com.nacro.validator.case.StringScope.wordAndDigitOnly
import com.nacro.validator.case.StringScope.validEmailFormat
import com.nacro.validator.constraint
import com.nacro.validator.check
import com.nacro.validator.injection.ValidateField

class LoginValidator {
// 帳號邏輯：
// - 非空
// - email
    @ValidateField("account")
    fun accountChecker(account: String) = check(account) {
        constraint(
            checkAll = true,
            conditions = arrayOf(
                alertNonEmpty(),
                validEmailFormat()
            )
        )
    }
// 密碼邏輯:
// - 非空
// - 長度 8 ~ 16
// - 中英混合
// - 不能有空白, $%^&等特殊符號
    @ValidateField("password")
    fun passwordChecker(password: String) = check(password) {
        constraint(
            checkAll = true,
            conditions = arrayOf(
                alertNonEmpty(),
                validSize(8..16),
                wordAndDigitOnly()
            )
        )
    }

}