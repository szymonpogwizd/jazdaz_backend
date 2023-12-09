package jazdaz.JazdaZ.service;

import jazdaz.JazdaZ.database.vehicle.VehicleEntity;
import jazdaz.JazdaZ.database.vehicle.VehicleRepository;
import jazdaz.JazdaZ.validator.VehicleValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleValidator vehicleValidator;

    @Transactional
    public VehicleEntity create(VehicleEntity vehicle) {
        log.debug("Creating vehicle {}", vehicle);

        vehicleValidator.validateVehicle(vehicle, false);

        return log.traceExit(vehicleRepository.save(vehicle));
    }

    @Transactional
    public VehicleEntity update(UUID id, VehicleEntity vehicle) {
        log.debug("Editing vehicle {} - {}", id, vehicle);
        boolean isSameVehicle = vehicleValidator.checkIfSameVehicle(id, vehicle);
        vehicleValidator.validateVehicle(vehicle, isSameVehicle);
        VehicleEntity toUpdate = vehicleRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Vehicle with id " + id + " was not found"));

        toUpdate.setBrand(vehicle.getBrand());
        toUpdate.setModel(vehicle.getModel());
        toUpdate.setYear(vehicle.getYear());
        toUpdate.setRegistrationNumber(vehicle.getRegistrationNumber());
        toUpdate.setColor(vehicle.getColor());
        toUpdate.setCourseCategory(vehicle.getCourseCategory());

        return log.traceExit(vehicleRepository.save(toUpdate));
    }

    public void delete(UUID id) {
        log.debug("Deleting vehicle {}", id);
        vehicleRepository.deleteById(id);
    }

    public List<VehicleEntity> getAll() {
        log.debug("Getting all vehicles");
        return log.traceExit(vehicleRepository.findAll());
    }
}
