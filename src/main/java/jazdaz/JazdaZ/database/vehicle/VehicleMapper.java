package jazdaz.JazdaZ.database.vehicle;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface VehicleMapper {

    VehicleInfoDTO vehicleEntity2VehicleInfoDTO(VehicleEntity vehicleEntity);

    VehicleEntity vehicleCreateDTO2VehicleEntity(VehicleCreateDTO vehicleCreateDTO);

    VehicleEntity vehicleUpdateDTO2VehicleEntity(VehicleUpdateDTO vehicleUpdateDTO);
}
