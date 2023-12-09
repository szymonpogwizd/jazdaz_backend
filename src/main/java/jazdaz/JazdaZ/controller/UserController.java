package jazdaz.JazdaZ.controller;

import jazdaz.JazdaZ.database.user.*;
import jazdaz.JazdaZ.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserInfoDTO createUser(@RequestBody @Valid UserCreateDTO user) {
        log.debug("Create user {}", user);
        UserEntity toCreate = userMapper.userCreateDTO2UserEntity(user);
        UserEntity createdUser = userService.create(toCreate);
        return log.traceExit(userMapper.userEntity2UserInfoDTO(createdUser));
    }

    @PutMapping("{id}")
    public UserInfoDTO updateUser(@RequestBody @Valid UserUpdateDTO user, @PathVariable UUID id) {
        log.debug("Update user {}: {}", id, user);
        UserEntity updatedUser = userService.update(id, userMapper.userUpdateDTO2UserEntity(user));
        return log.traceExit(userMapper.userEntity2UserInfoDTO(updatedUser));
    }

    @GetMapping
    public List<UserInfoDTO> getAll() {
        log.debug("Getting all users");
        return log.traceExit(
                userService.getAll()
                        .stream()
                        .map(userMapper::userEntity2UserInfoDTO)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/userTypes")
    public List<UserType> getUserType() {
        log.debug("Getting all user types");
        return log.traceExit(
                new ArrayList<>(userService.getAllUserTypes())
        );
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable UUID id) {
        log.debug("Delete user {}", id);
        userService.delete(id);
    }
}
