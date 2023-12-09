package jazdaz.JazdaZ.validator;

import jazdaz.JazdaZ.database.user.UserEntity;
import jazdaz.JazdaZ.database.user.UserRepository;
import jazdaz.JazdaZ.database.user.UserType;
import jazdaz.JazdaZ.validator.email.EmailValidator;
import jazdaz.JazdaZ.validator.email.EmailValidatorException;
import jazdaz.JazdaZ.validator.password.PasswordValidator;
import jazdaz.JazdaZ.validator.password.PasswordValidatorException;
import jazdaz.JazdaZ.validator.phone.PhoneValidator;
import jazdaz.JazdaZ.validator.phone.PhoneValidatorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validateUser(UserEntity user, boolean isSameUser) {
        List<String> validationErrors = new ArrayList<>();

        validateName(user, validationErrors);
        if (!isSameUser) {
            validateContactInfo(user, validationErrors);
        }
        validateEmail(user, validationErrors);
        validatePassword(user, validationErrors);
        validatePhone(user, validationErrors);

        if (validationErrors.isEmpty()) {
            String errorMessage = String.join("", validationErrors);
            throw new ValidationException(errorMessage);
        }
    }

    private static void validateName(UserEntity user, List<String> validationErrors) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            validationErrors.add("Imię nie może być puste\n");
        }

        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            validationErrors.add("Nazwisko nie może być puste\n");
        }
    }

    private void validateContactInfo(UserEntity user, List<String> validationErrors) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            validationErrors.add("Użytkownik o podanym adresie e-mail już istnieje\n");
        }

        if (userRepository.findByPhone(user.getPhone()).isPresent()) {
            validationErrors.add("Użytkownik o podanym numerze telefonu już istnieje\n");
        }
    }

    private static void validateEmail(UserEntity user, List<String> validationErrors) {
        try {
            EmailValidator.validate(user.getEmail());
        } catch (EmailValidatorException e) {
            validationErrors.add(e.getMessage() + "\n");
        }
    }

    private static void validatePassword(UserEntity user, List<String> validationErrors) {
        try {
            PasswordValidator.validate(user.getPassword());
        } catch (PasswordValidatorException e) {
            validationErrors.add(e.getMessage() + "\n");
        }
    }

    private static void validatePhone(UserEntity user, List<String> validationErrors) {
        try {
            PhoneValidator.validate(user.getPhone());
        } catch (PhoneValidatorException e) {
            validationErrors.add(e.getMessage() + "\n");
        }
    }

    public boolean checkIfSameUser(UUID id, UserEntity user) {
        Optional<UserEntity> foundUserByEmail = userRepository.findByEmail(user.getEmail());
        Optional<UserEntity> foundUserByPhone = userRepository.findByPhone(user.getPhone());
        boolean isSameUserByEmail = foundUserByEmail.isPresent() && foundUserByEmail.get().getId().equals(id);
        boolean isSameUserByPhone = foundUserByPhone.isPresent() && foundUserByPhone.get().getId().equals(id);
        return isSameUserByEmail && isSameUserByPhone;
    }

    public void parseUserType(UserEntity user) {
        UserType userType = UserType.valueOf(String.valueOf(String.valueOf(user.getUserType())));

        if (userType == UserType.USER) {
            user.setUserType(UserType.USER);
        } else if (userType == UserType.ADMINISTRATOR) {
            user.setUserType(UserType.ADMINISTRATOR);
        } else if (userType == UserType.SUPER_ADMINISTRATOR) {
            user.setUserType(UserType.SUPER_ADMINISTRATOR);
        }
    }
}
