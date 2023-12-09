package jazdaz.JazdaZ.database.course.courseCategory;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jazdaz.JazdaZ.database.course.CourseEntity;
import jazdaz.JazdaZ.database.vehicle.VehicleEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "courseCategories")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CourseEntity.class)
public class CourseCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
    private String drivingLicenceCategory;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "courseCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseEntity> courses;

    @OneToMany(mappedBy = "courseCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VehicleEntity> vehicles;

    public CourseCategoryEntity() {
    }

    public CourseCategoryEntity(UUID id, String drivingLicenceCategory, String name, List<CourseEntity> courses, List<VehicleEntity> vehicles) {
        this.id = id;
        this.drivingLicenceCategory = drivingLicenceCategory;
        this.name = name;
        this.courses = courses;
        this.vehicles = vehicles;
    }
}
