package jazdaz.JazdaZ.database.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, UUID> {

    Optional<VehicleEntity> findByRegistrationNumber(String registrationNumber);
}
