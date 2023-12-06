package jazdaz.JazdaZ.database.user;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jazdaz.JazdaZ.database.course.CourseEntity;
import jazdaz.JazdaZ.validator.email.Email;
import jazdaz.JazdaZ.validator.password.Password;
import jazdaz.JazdaZ.validator.phone.Phone;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = UserEntity.class)
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
    @Email
    private String email;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
    @Phone
    private String phone;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty
    @Password
    private String password;

    @Column(columnDefinition = "text")
    private String token;

    private ZonedDateTime tokenExpiration;

    @ManyToMany
    @JoinTable(
            name = "usersCourses",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "coursesId"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<CourseEntity> courses;
}
