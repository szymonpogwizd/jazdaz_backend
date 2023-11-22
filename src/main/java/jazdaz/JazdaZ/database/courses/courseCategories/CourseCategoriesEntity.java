package jazdaz.JazdaZ.database.courses.courseCategories;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity
@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "courseCategories")
public class CourseCategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
    private String drivingLicenceCategory;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
    private String name;
}
