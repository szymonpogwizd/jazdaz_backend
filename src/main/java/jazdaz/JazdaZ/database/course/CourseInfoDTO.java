package jazdaz.JazdaZ.database.course;

import jazdaz.JazdaZ.database.course.courseCategory.CourseCategoryEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CourseInfoDTO {

    private UUID id;

    private String status;

    private LocalDate startDate;

    private CourseCategoryEntity courseCategory;
}
