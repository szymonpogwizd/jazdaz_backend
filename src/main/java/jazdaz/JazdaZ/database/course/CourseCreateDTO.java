package jazdaz.JazdaZ.database.course;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class CourseCreateDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank
    @Size(min = 1, max = 5000)
    private String status;

    private LocalDate startDate;

    private UUID courseCategoryId;

    private List<UUID> usersIds;
}
