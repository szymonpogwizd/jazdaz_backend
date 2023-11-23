package jazdaz.JazdaZ.database.vehicle;

import jazdaz.JazdaZ.database.course.courseCategory.CourseCategoryEntity;
import lombok.Data;

import java.util.UUID;

@Data
public class VehicleInfoDTO {

    private UUID id;

    private String brand;

    private String model;

    private int year;

    private String registrationNumber;

    private String color;

    private CourseCategoryEntity courseCategory;
}
