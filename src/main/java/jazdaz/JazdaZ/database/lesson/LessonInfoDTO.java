package jazdaz.JazdaZ.database.lesson;

import jazdaz.JazdaZ.database.course.CourseEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class LessonInfoDTO {

    private UUID id;

    private String type;

    private int hours_spend;

    private LocalDate start_date;

    private LocalDate end_date;

    private CourseEntity course;
}
