package jazdaz.JazdaZ.database.vehicles;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity
@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "vehicles")
public class VehiclesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "text")
    private String brand;

    @Column(columnDefinition = "text")
    private String model;

    @Column
    private int year;

    @Column(columnDefinition = "text", nullable = false, unique = true)
    @NotEmpty
    private String registrationNumber;

    @Column(columnDefinition = "text")
    private String color;
}
