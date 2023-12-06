package jazdaz.JazdaZ.database.course.courseCategory;

import lombok.Data;

import java.util.UUID;

@Data
public class CourseCategoryInfoDTO {

    private UUID id;

    private String drivingLicenceCategory;

    private String name;
}
