package jazdaz.JazdaZ.database.vehicle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jazdaz.JazdaZ.database.course.courseCategory.CourseCategoryEntity;
import jazdaz.JazdaZ.database.lesson.LessonEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity
@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "vehicles")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = VehicleEntity.class)
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "text")
    private String brand;

    @Column(columnDefinition = "text")
    private String model;

    @Column
    private int year;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
    private String registrationNumber;

    @Column(columnDefinition = "text")
    private String color;

    @OneToOne(mappedBy = "vehicle")
    private LessonEntity lesson;

    @ManyToOne
    @JoinColumn(name = "courseCategoryId")
    private CourseCategoryEntity courseCategory;

    public VehicleEntity() {
    }

    public VehicleEntity(UUID id, String brand, String model, int year, String registrationNumber, String color, LessonEntity lesson, CourseCategoryEntity courseCategory) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.lesson = lesson;
        this.courseCategory = courseCategory;
    }
}
