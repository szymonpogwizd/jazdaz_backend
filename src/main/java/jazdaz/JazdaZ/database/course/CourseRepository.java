package jazdaz.JazdaZ.database.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

    Optional<CourseEntity> findByName(String name);
}
