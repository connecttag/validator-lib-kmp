package org.connecttag.lib.validator

/**
 * Validates password based on strength rules.
 */
public class PasswordValidator(
    private val minLength: Int = 8,
    private val requireUppercase: Boolean = true,
    private val requireLowercase: Boolean = true,
    private val requireDigit: Boolean = true,
    private val requireSpecialChar: Boolean = false,
    private val errorMessages: PasswordErrorMessages = PasswordErrorMessages(),
) : Validator<String?> {

    public data class PasswordErrorMessages(
        val tooShort: String = "Password is too short",
        val noUppercase: String = "Password must contain an uppercase letter",
        val noLowercase: String = "Password must contain a lowercase letter",
        val noDigit: String = "Password must contain a digit",
        val noSpecial: String = "Password must contain a special character",
    )

    override fun validate(value: String?): ValidationResult {
        if (value == null) return ValidationResult.Error(errorMessages.tooShort)

        if (value.length < minLength) {
            return ValidationResult.Error(errorMessages.tooShort)
        }

        if (requireUppercase && !value.any { it.isUpperCase() }) {
            return ValidationResult.Error(errorMessages.noUppercase)
        }

        if (requireLowercase && !value.any { it.isLowerCase() }) {
            return ValidationResult.Error(errorMessages.noLowercase)
        }

        if (requireDigit && !value.any { it.isDigit() }) {
            return ValidationResult.Error(errorMessages.noDigit)
        }

        if (requireSpecialChar && value.all { it.isLetterOrDigit() }) {
            return ValidationResult.Error(errorMessages.noSpecial)
        }

        return ValidationResult.Success
    }
}
