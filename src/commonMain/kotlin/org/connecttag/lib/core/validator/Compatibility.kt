package org.connecttag.lib.core.validator.core

public typealias Validator<T> = org.connecttag.lib.validator.Validator<T>
public typealias ValidationResult = org.connecttag.lib.validator.ValidationResult
public typealias CompositeValidator<T> = org.connecttag.lib.validator.CompositeValidator<T>
public typealias ValidatorBuilder<T> = org.connecttag.lib.validator.ValidatorBuilder<T>

public fun <T> buildValidator(block: org.connecttag.lib.validator.ValidatorBuilder<T>.() -> Unit): org.connecttag.lib.validator.Validator<T> =
    org.connecttag.lib.validator.buildValidator(block)

public fun validateFields(vararg pairs: Pair<String, org.connecttag.lib.validator.ValidationResult>): org.connecttag.lib.validator.ValidationResult =
    org.connecttag.lib.validator.validateFields(*pairs)
