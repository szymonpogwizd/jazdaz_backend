package jazdaz.JazdaZ.database.user;

import jazdaz.JazdaZ.validator.email.Email;
import jazdaz.JazdaZ.validator.password.Password;
import jazdaz.JazdaZ.validator.phone.Phone;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserCreateDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 100)
    private String lastName;

    @NotBlank
    private UserType userType;

    @Email
    @NotBlank
    private String email;

    @Phone
    @NotBlank
    private String phone;

    @Password
    @NotBlank
    private String password;
}
