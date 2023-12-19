package jazdaz.JazdaZ.controller;

import jazdaz.JazdaZ.database.vehicle.*;
import jazdaz.JazdaZ.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
@CrossOrigin
public class VehicleController {

    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;

    @PostMapping
    public VehicleInfoDTO createVehicle(@RequestBody @Valid VehicleCreateDTO vehicle) {
        log.debug("Create vehicle {}", vehicle);
        VehicleEntity toCreate = vehicleMapper.vehicleCreateDTO2VehicleEntity(vehicle);
        VehicleEntity createdVehicle = vehicleService.create(toCreate);
        return log.traceExit(vehicleMapper.vehicleEntity2VehicleInfoDTO(createdVehicle));
    }

    @PutMapping("{id}")
    public VehicleInfoDTO updateVehicle(@RequestBody @Valid VehicleUpdateDTO vehicle, @PathVariable UUID id) {
        log.debug("Update vehicle {}: {}", id, vehicle);
        VehicleEntity updatedVehicle = vehicleService.update(id, vehicleMapper.vehicleUpdateDTO2VehicleEntity(vehicle));
        return log.traceExit(vehicleMapper.vehicleEntity2VehicleInfoDTO(updatedVehicle));
    }

    @GetMapping
    public List<VehicleInfoDTO> getAll() {
        log.debug("Getting all vehicles");
        return log.traceExit(
                vehicleService.getAll()
                        .stream()
                        .map(vehicleMapper::vehicleEntity2VehicleInfoDTO)
                        .collect(java.util.stream.Collectors.toList())
        );
    }

    @GetMapping("{id}")
    public VehicleInfoDTO getVehicle(@PathVariable UUID id) {
        log.debug("Getting vehicle {}", id);
        return log.traceExit(
                vehicleService.getVehicle(id)
                        .map(vehicleMapper::vehicleEntity2VehicleInfoDTO)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found"))
        );
    }

    @DeleteMapping("{id}")
    public void deleteVehicle(@PathVariable UUID id) {
        log.debug("Deleting vehicle {}", id);
        vehicleService.delete(id);
    }
}
