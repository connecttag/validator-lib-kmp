package org.connecttag.lib.validator

/**
 * DSL for building a composite validator.
 */
public class ValidatorBuilder<T> {
    private val validators = mutableListOf<Validator<T>>()

    public fun add(validator: Validator<T>) {
        validators.add(validator)
    }

    public fun build(): Validator<T> = CompositeValidator(validators)
}

public fun <T> buildValidator(block: ValidatorBuilder<T>.() -> Unit): Validator<T> {
    return ValidatorBuilder<T>().apply(block).build()
}

/**
 * Extension to validate multiple fields at once.
 */
public fun validateFields(vararg pairs: Pair<String, ValidationResult>): ValidationResult {
    for (pair in pairs) {
        if (pair.second is ValidationResult.Error) {
            return pair.second
        }
    }
    return ValidationResult.Success
}
