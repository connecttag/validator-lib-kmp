package org.connecttag.lib.validator

/**
 * Validates that a string is not empty or blank.
 */
public class RequiredValidator(private val errorMessage: String = "Field is required") : Validator<String?> {
    override fun validate(value: String?): ValidationResult {
        return if (value.isNullOrBlank()) {
            ValidationResult.Error(errorMessage)
        } else {
            ValidationResult.Success
        }
    }
}

/**
 * Validates that a string matches a regular expression.
 */
public class RegexValidator(
    private val pattern: String,
    private val errorMessage: String,
) : Validator<String?> {
    private val regex = Regex(pattern)

    override fun validate(value: String?): ValidationResult {
        if (value.isNullOrEmpty()) return ValidationResult.Success
        return if (regex.matches(value)) {
            ValidationResult.Success
        } else {
            ValidationResult.Error(errorMessage)
        }
    }
}

/**
 * Validates email format.
 */
public class EmailValidator(private val errorMessage: String = "Invalid email format") : Validator<String?> {
    private val pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    private val delegate = RegexValidator(pattern, errorMessage)

    override fun validate(value: String?): ValidationResult = delegate.validate(value)
}

/**
 * Validates phone number format (basic).
 */
public class PhoneValidator(private val errorMessage: String = "Invalid phone number") : Validator<String?> {
    private val pattern = "^[+]?[0-9]{10,15}$"
    private val delegate = RegexValidator(pattern, errorMessage)

    override fun validate(value: String?): ValidationResult = delegate.validate(value)
}
