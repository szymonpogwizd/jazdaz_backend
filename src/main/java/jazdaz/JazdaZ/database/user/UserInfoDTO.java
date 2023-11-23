package jazdaz.JazdaZ.database.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserInfoDTO {

    private UUID id;

    private String firstName;

    private String lastName;

    private UserType userType;

    private String email;

    private String phone;
}
