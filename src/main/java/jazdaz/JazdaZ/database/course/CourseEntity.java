package jazdaz.JazdaZ.database.course;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jazdaz.JazdaZ.database.course.courseCategory.CourseCategoryEntity;
import jazdaz.JazdaZ.database.lesson.LessonEntity;
import jazdaz.JazdaZ.database.user.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "courses")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CourseEntity.class)
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @NotEmpty
    private String name;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty
    private String status;

    @Column
    private LocalDate startDate;

    @ManyToMany(mappedBy = "courses")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<UserEntity> users;

    @ManyToOne
    @JoinColumn(name = "courseCategoryId")
    private CourseCategoryEntity courseCategory;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LessonEntity> lessons;

    public CourseEntity() {
    }

    public CourseEntity(UUID id, String name, String status, LocalDate startDate, List<UserEntity> users, CourseCategoryEntity courseCategory, List<LessonEntity> lessons) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.users = users;
        this.courseCategory = courseCategory;
        this.lessons = lessons;
    }
}
