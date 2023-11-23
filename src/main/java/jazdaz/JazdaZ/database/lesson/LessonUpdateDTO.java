package jazdaz.JazdaZ.database.lesson;

import jakarta.validation.constraints.NotBlank;
import jazdaz.JazdaZ.database.course.CourseEntity;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class LessonUpdateDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String type;

    @NotBlank
    private int hours_spend;

    private LocalDate start_date;

    private LocalDate end_date;

    private CourseEntity course;
}
