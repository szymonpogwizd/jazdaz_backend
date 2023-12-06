package jazdaz.JazdaZ.database.course;

import jazdaz.JazdaZ.database.course.courseCategory.CourseCategoryEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class CourseUpdateDTO {

    @NotBlank
    @Size(min = 1, max = 5000)
    private String status;

    private LocalDate startDate;

    private CourseCategoryEntity courseCategory;
}