# Validator

Just for pratice~

## Version: v0.1.0

## Update Note:

### 登入邏輯模擬
- Encapsulation: 登入商業邏輯封裝
- Add: Login test case. (`com.nacro.validator.test.TestLoginValidate.kt`)

### Injection
- new feature: 自動匹配 model進行驗證. `com.nacro.validator.injection.DataValidatorInjection.kt`
- Test case: login data validate. (`com.nacro.validator.test.TestLoginData.kt`)

用法:

假設LoginData model 如下
```
data class LoginData(val account: String, val password: String)
```

1. 創建 class, 寫下對驗證函式. 並在對應的該函式, 使用@ValidateField("欲驗證的 model 變數名稱")
ex:
```
class LoginValidator {
// 帳號邏輯：
// - 非空
// - email
    @ValidateField("account") // 對應LoginData 裡的account
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
    @ValidateField("password") // 對應LoginData 裡的password
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
```

2. 進行驗證
```
fun testLoginData1(validator: LoginValidator) {
    val data = LoginData("wdwfe@asc.com", "711092")
    val results = DataValidatorInjection.inject(data, validator)
    results.forEach { entry ->
        println("name: ${entry.key}, error msg: [\n${entry.value.message}\n]")
    }
}
```