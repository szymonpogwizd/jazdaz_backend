package jazdaz.JazdaZ.database.course.courseCategory;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CourseUpdateDTO {

    @Size(min = 1, max = 10)
    @NotBlank
    private String drivingLicenceCategory;

    @Size(min = 1, max = 100)
    @NotBlank
    private String name;
}
