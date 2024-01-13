package jazdaz.JazdaZ.database.lesson;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class LessonCreateDTO {

    @NotBlank
    private String type;

    @NotBlank
    private int hours_spend;

    private LocalDate start_date;

    private LocalDate end_date;

    private UUID courseId;
}
