package jazdaz.JazdaZ.database.lesson;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class LessonUpdateDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String type;

    @NotBlank
    private int hours_spend;

    private LocalDate start_date;

    private LocalDate end_date;

    private UUID courseId;
}
