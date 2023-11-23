package jazdaz.JazdaZ.validator.phone;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Phone number validation failed")
public class PhoneValidatorException extends RuntimeException {

    PhoneValidatorException(String message) {
        super(message);
    }
}
