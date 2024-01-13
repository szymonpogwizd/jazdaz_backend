package jazdaz.JazdaZ.database.vehicle;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class VehicleUpdateDTO {

    @Size(min = 1, max = 100)
    private String brand;

    @Size(min = 1, max = 100)
    private String model;

    private int year;

    @NotBlank
    @Size(min = 5, max = 10)
    private String registrationNumber;

    @Size(min = 1, max = 100)
    private String color;

    private UUID courseCategoryId;
}
