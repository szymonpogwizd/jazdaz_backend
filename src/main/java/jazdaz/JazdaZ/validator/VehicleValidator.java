package jazdaz.JazdaZ.validator;

import jazdaz.JazdaZ.database.vehicle.VehicleEntity;
import jazdaz.JazdaZ.database.vehicle.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VehicleValidator {

    private final VehicleRepository vehicleRepository;

    public boolean checkIfSameVehicle(UUID id, VehicleEntity vehicle) {
        Optional<VehicleEntity> foundVehicle = vehicleRepository.findByRegistrationNumber(vehicle.getRegistrationNumber());
        return foundVehicle.isPresent() && foundVehicle.get().getId().equals(id);
    }

    public void validateVehicle(VehicleEntity vehicle, boolean isSameVehicle) {
        List<String> validationErrors = new ArrayList<>();

        validateRegistrationNumber(vehicle, validationErrors);

        if (!isSameVehicle) {
            validateUniqueRegistrationNumber(vehicle, validationErrors);
        }

        if (!validationErrors.isEmpty()) {
            String errorMessage = String.join("", validationErrors);
            throw new ValidationException(errorMessage);
        }
    }

    private void validateRegistrationNumber(VehicleEntity vehicle, List<String> validationErrors) {
        if (vehicle.getRegistrationNumber() == null || vehicle.getRegistrationNumber().isEmpty()) {
            validationErrors.add("Numer rejestracyjny nie może być pusty\n");
        }
    }

    private void validateUniqueRegistrationNumber(VehicleEntity vehicle, List<String> validationErrors) {
        if (vehicleRepository.findByRegistrationNumber(vehicle.getRegistrationNumber()).isPresent()) {
            validationErrors.add("Pojazd o podanym numerze rejestracyjnym już istnieje\n");
        }
    }
}
