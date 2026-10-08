package org.connecttag.lib.validator

public class UrlValidator(
    private val requireHttps: Boolean = false,
    private val errorMessage: String = "Invalid URL",
) : Validator<String?> {
    private val urlRegex = Regex("^https?://[^\\s/$.?#].[^\\s]*$", RegexOption.IGNORE_CASE)

    override fun validate(value: String?): ValidationResult {
        if (value.isNullOrBlank()) return ValidationResult.Success
        val valid = urlRegex.matches(value) && (!requireHttps || value.startsWith("https://", ignoreCase = true))
        return if (valid) ValidationResult.Success else ValidationResult.Error(errorMessage)
    }
}

public class FileNameValidator(
    private val allowBlank: Boolean = false,
    private val errorMessage: String = "Invalid file name",
) : Validator<String?> {
    private val forbiddenCharacters = setOf('<', '>', ':', '"', '/', '\\', '|', '?', '*')

    override fun validate(value: String?): ValidationResult {
        val fileName = value.orEmpty()
        val invalid = (!allowBlank && fileName.isBlank()) ||
            fileName.any { it in forbiddenCharacters } ||
            fileName == "." ||
            fileName == ".."
        return if (invalid) ValidationResult.Error(errorMessage) else ValidationResult.Success
    }
}

public class NormalizedPhoneValidator(
    private val minDigits: Int = 7,
    private val maxDigits: Int = 15,
    private val errorMessage: String = "Invalid phone number",
) : Validator<String?> {
    override fun validate(value: String?): ValidationResult {
        if (value.isNullOrBlank()) return ValidationResult.Success
        val normalized = value.filter { it.isDigit() || it == '+' }
        val digits = normalized.count(Char::isDigit)
        val hasValidPlus = normalized.indexOf('+').let { index -> index == -1 || index == 0 }
        val valid = hasValidPlus && digits in minDigits..maxDigits
        return if (valid) ValidationResult.Success else ValidationResult.Error(errorMessage)
    }
}
