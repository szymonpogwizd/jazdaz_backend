package jazdaz.JazdaZ.service;

import jazdaz.JazdaZ.database.user.UserEntity;
import jazdaz.JazdaZ.database.user.UserRepository;
import jazdaz.JazdaZ.database.user.UserType;
import jazdaz.JazdaZ.utils.TokenUtility;
import jazdaz.JazdaZ.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {

    @Value("${app.user.token.activation.validity-days:7}")
    private int tokenValidity;
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Transactional
    public UserEntity create(UserEntity user) {
        log.debug("Creating user {}", user);

        userValidator.validateUser(user, false);

        user.setToken(TokenUtility.generate());
        user.setTokenExpiration(ZonedDateTime.now().plusDays(tokenValidity));

        userValidator.parseUserType(user);

        return log.traceExit(userRepository.save(user));
    }

    @Transactional
    public UserEntity update(UUID id, UserEntity user) {
        log.debug("Editing user {} - {}", id, user);
        boolean isSameUser = userValidator.checkIfSameUser(id, user);
        userValidator.validateUser(user, isSameUser);
        UserEntity toUpdate = userRepository.findById(id)
                .orElseThrow(() -> new ValidationException("User with id " + id + " was not found"));

        toUpdate.setFirstName(user.getFirstName());
        toUpdate.setLastName(user.getLastName());
        toUpdate.setEmail(user.getEmail());
        toUpdate.setPhone(user.getPhone());
        toUpdate.setPassword(user.getPassword());
        toUpdate.setCourses(user.getCourses());
        toUpdate.setToken(user.getToken());
        toUpdate.setTokenExpiration(user.getTokenExpiration());

        userValidator.parseUserType(toUpdate);

        return log.traceExit(userRepository.save(toUpdate));
    }

    public void delete(UUID id) {
        log.debug("Deleting user {}", id);
        userRepository.deleteById(id);
    }

    public List<UserEntity> getAll() {
        log.debug("Getting all users");
        return log.traceExit(userRepository.findAll());
    }

    public List<UserType> getAllUserTypes() {
        log.debug("Getting all user types");
        return log.traceExit(List.of(UserType.values()));
    }
}
