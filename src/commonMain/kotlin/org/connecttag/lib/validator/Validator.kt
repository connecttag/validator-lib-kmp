package org.connecttag.lib.validator

/**
 * Basic interface for all validators.
 */
public interface Validator<T> {
    public fun validate(value: T): ValidationResult
}

/**
 * Represents the result of a validation.
 */
public sealed class ValidationResult {
    public object Success : ValidationResult()
    public data class Error(public val message: String) : ValidationResult()

    public val isValid: Boolean get() = this is Success
}

/**
 * A validator that combines multiple validators.
 */
public class CompositeValidator<T>(private val validators: List<Validator<T>>) : Validator<T> {
    override fun validate(value: T): ValidationResult {
        for (validator in validators) {
            val result = validator.validate(value)
            if (result is ValidationResult.Error) {
                return result
            }
        }
        return ValidationResult.Success
    }
}
