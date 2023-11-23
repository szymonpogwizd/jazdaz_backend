package jazdaz.JazdaZ.validator.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("\\+?\\d{1,3}\\d{9,10}");

    public static void validate(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new PhoneValidatorException("Numer telefonu nie może być pusty");
        }
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new PhoneValidatorException("Niepoprawny format numeru telefonu");
        }
    }

    @Override
    public void initialize(Phone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.isBlank() && PHONE_PATTERN.matcher(value).matches();
    }
}
