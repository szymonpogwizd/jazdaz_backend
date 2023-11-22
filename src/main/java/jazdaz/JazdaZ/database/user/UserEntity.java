package jazdaz.JazdaZ.database.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty
    private String firstName;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty
    private String lastName;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
//    @Email
    private String email;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
//    @Phone
    private String phone;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty
//    @Password
    private String password;

    @Column(columnDefinition = "text")
    private String token;

    private ZonedDateTime tokenExpiration;
}
