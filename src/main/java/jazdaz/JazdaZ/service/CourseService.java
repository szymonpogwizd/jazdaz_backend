package jazdaz.JazdaZ.service;

import jazdaz.JazdaZ.database.course.CourseEntity;
import jazdaz.JazdaZ.database.course.CourseRepository;
import jazdaz.JazdaZ.validator.CourseValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseValidator courseValidator;

    @Transactional
    public CourseEntity create(CourseEntity course) {
        log.debug("Creating course {}", course);

        courseValidator.validateCourse(course, false);

        return log.traceExit(courseRepository.save(course));
    }

    @Transactional
    public CourseEntity update(UUID id, CourseEntity course) {
        log.debug("Editing course {} - {}", id, course);
        boolean isSameCourse = courseValidator.checkIfSameCourse(id, course);
        courseValidator.validateCourse(course, isSameCourse);
        CourseEntity toUpdate = courseRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Course with id " + id + " was not found"));

        toUpdate.setStatus(course.getStatus());
        toUpdate.setStartDate(course.getStartDate());
        toUpdate.setUsers(course.getUsers());
        toUpdate.setCourseCategory(course.getCourseCategory());
        toUpdate.setLessons(course.getLessons());

        return log.traceExit(courseRepository.save(toUpdate));
    }

    public void delete(UUID id) {
        log.debug("Deleting course {}", id);
        courseRepository.deleteById(id);
    }

    public List<CourseEntity> getAll() {
        log.debug("Getting all courses");
        return log.traceExit(courseRepository.findAll());
    }

    public Optional<CourseEntity> getCourse(UUID id) {
        log.debug("Getting course {}", id);
        return log.traceExit(courseRepository.findById(id));
    }
}
