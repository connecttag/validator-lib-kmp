package org.connecttag.lib.validator

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidatorCommonTest {

    @Test
    fun testRequiredValidator() {
        val validator = RequiredValidator()
        assertTrue(validator.validate("Hello").isValid)
        assertFalse(validator.validate("").isValid)
        assertFalse(validator.validate("   ").isValid)
        assertFalse(validator.validate(null).isValid)
    }

    @Test
    fun testEmailValidator() {
        val validator = EmailValidator()
        assertTrue(validator.validate("test@example.com").isValid)
        assertTrue(validator.validate("user.name+tag@sub.domain.co").isValid)
        assertFalse(validator.validate("invalid-email").isValid)
        assertFalse(validator.validate("test@").isValid)
    }

    @Test
    fun testPasswordValidator() {
        val validator = PasswordValidator(minLength = 8, requireUppercase = true, requireDigit = true)
        assertTrue(validator.validate("SecurePass123").isValid)
        assertFalse(validator.validate("short1").isValid)
        assertFalse(validator.validate("lowercaseonly123").isValid)
        assertFalse(validator.validate("NoDigitsHere").isValid)
    }

    @Test
    fun testUrlValidator() {
        val validator = UrlValidator(requireHttps = true)
        assertTrue(validator.validate("https://connecttag.org").isValid)
        assertFalse(validator.validate("http://insecure.org").isValid)
    }

    @Test
    fun testCompositeValidatorDSL() {
        val validator = buildValidator<String?> {
            add(RequiredValidator())
            add(EmailValidator())
        }
        assertTrue(validator.validate("info@connecttag.org").isValid)
        assertFalse(validator.validate("").isValid)
        assertFalse(validator.validate("invalid").isValid)
    }
}
