package jazdaz.JazdaZ.database.course.courseCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseCategoryRepository extends JpaRepository<CourseCategoryEntity, UUID> {

    Optional<CourseCategoryEntity> findByName(String name);
    Optional<CourseCategoryEntity> findByDrivingLicenceCategory(String drivingLicenceCategory);
}
