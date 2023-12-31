package jazdaz.JazdaZ.database.lesson;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jazdaz.JazdaZ.database.course.CourseEntity;
import jazdaz.JazdaZ.database.vehicle.VehicleEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "lessons")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = LessonEntity.class)
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty
    private String type;

    @Column(nullable = false)
    @NotEmpty
    private int hours_spend;

    @Column
    private LocalDate start_date;

    @Column
    private LocalDate end_date;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private CourseEntity course;

    @OneToOne
    @JoinColumn(name = "vehicleId")
    private VehicleEntity vehicle;

    public LessonEntity() {
    }

    public LessonEntity(UUID id, String type, int hours_spend, LocalDate start_date, LocalDate end_date, CourseEntity course, VehicleEntity vehicle) {
        this.id = id;
        this.type = type;
        this.hours_spend = hours_spend;
        this.start_date = start_date;
        this.end_date = end_date;
        this.course = course;
        this.vehicle = vehicle;
    }
}
