# ConnectTag Validator Library (validator-lib-kmp)

[![JitPack](https://jitpack.io/v/connecttag/validator-lib-kmp.svg)](https://jitpack.io/#connecttag/validator-lib-kmp)

محرك التحقق من صحة المدخلات والبيانات متعدد المنصات (Kotlin Multiplatform):
- **Android** (API 24+)
- **Desktop JVM** (Windows, macOS, Linux)
- **iOS** (Arm64, SimulatorArm64)

---

## 📦 التثبيت والإضافة للمشروع (Installation via JitPack)

### 1. إضافة مستودع JitPack في `settings.gradle.kts`:
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### 2. إضافة التبعية في `build.gradle.kts`:
```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            // For Kotlin Multiplatform:
            implementation("com.github.connecttag.validator-lib-kmp:validator-lib-kmp:1.0.0")
        }
    }
}
```

الحزمة الأساسية للمكتبة: `org.connecttag.lib.validator.*` أو `org.connecttag.lib.core.validator.core.*`

---

## 🌟 الميزات والاستخدام

### 1. التحقق من المدخلات الفردية:
```kotlin
val emailValidator = EmailValidator()
val result = emailValidator.validate("test@example.com")
if (result.isValid) {
    // البريد صالح
} else if (result is ValidationResult.Error) {
    println(result.message)
}
```

### 2. بناء قواعد مركبة عبر الـ DSL:
```kotlin
val signupEmailValidator = buildValidator<String?> {
    add(RequiredValidator("البريد الإلكتروني مطلوب"))
    add(EmailValidator("صيغة البريد الإلكتروني غير صحيحة"))
}

val result = signupEmailValidator.validate(emailInput)
```

### 3. التحقق من قوة كلمات المرور:
```kotlin
val passwordValidator = PasswordValidator(
    minLength = 8,
    requireUppercase = true,
    requireLowercase = true,
    requireDigit = true,
    requireSpecialChar = true
)
```

### 4. التحقق من عدة حقول دفعة واحدة:
```kotlin
val result = validateFields(
    "email" to emailValidator.validate(email),
    "password" to passwordValidator.validate(password)
)
```
