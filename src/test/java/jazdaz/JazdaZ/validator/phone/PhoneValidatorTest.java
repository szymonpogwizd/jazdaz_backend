package jazdaz.JazdaZ.validator.phone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.validation.ConstraintValidatorContext;
import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {

    private PhoneValidator phoneValidator;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        phoneValidator = new PhoneValidator();
    }

    @Test
    void shouldValidateWhenPhoneNumberIsValid() {
        assertTrue(phoneValidator.isValid("+48123456789", context));
        assertTrue(phoneValidator.isValid("1234567890", context));
        assertTrue(phoneValidator.isValid("+123456789012", context));
    }

    @Test
    void shouldInvalidateWhenPhoneNumberIsInvalid() {
        assertFalse(phoneValidator.isValid("1234567", context));
        assertFalse(phoneValidator.isValid("+12 1234567890", context));
        assertFalse(phoneValidator.isValid("abcdefghijk", context));
    }

    @Test
    void shouldInvalidateWhenPhoneNumberIsNull() {
        assertFalse(phoneValidator.isValid(null, context));
    }

    @Test
    void shouldInvalidateWhenPhoneNumberIsEmpty() {
        assertFalse(phoneValidator.isValid("", context));
        assertFalse(phoneValidator.isValid("   ", context));
    }}
