package jazdaz.JazdaZ.validator;

import jazdaz.JazdaZ.database.course.CourseEntity;
import jazdaz.JazdaZ.database.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CourseValidator {

    private final CourseRepository courseRepository;

    public boolean checkIfSameCourse(UUID id, CourseEntity course) {
        Optional<CourseEntity> foundCourse = courseRepository.findByName(course.getName());
        return foundCourse.isPresent() && foundCourse.get().getId().equals(id);
    }

    public void validateCourse(CourseEntity course, boolean isSameCourse) {
        List<String> validationErrors = new ArrayList<>();

        validateName(course, validationErrors);
        validateStatus(course, validationErrors);
        if (!isSameCourse) {
            validateUniqueName(course, validationErrors);
        }

        if (!validationErrors.isEmpty()) {
            String errorMessage = String.join("", validationErrors);
            throw new ValidationException(errorMessage);
        }
    }

    private void validateName(CourseEntity course, List<String> validationErrors) {
        if (course.getName() == null || course.getName().isEmpty()) {
            validationErrors.add("Nazwa kursu nie może być pusta\n");
        }
    }

    private void validateStatus(CourseEntity course, List<String> validationErrors) {
        if (course.getStatus() == null || course.getStatus().isEmpty()) {
            validationErrors.add("Status kursu nie może być pusty\n");
        }
    }

    private void validateUniqueName(CourseEntity course, List<String> validationErrors) {
        if (courseRepository.findByName(course.getName()).isPresent()) {
            validationErrors.add("Kurs o podanej nazwie już istnieje\n");
        }
    }
}
