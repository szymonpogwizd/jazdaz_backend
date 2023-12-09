package jazdaz.JazdaZ.validator;

import jazdaz.JazdaZ.database.course.courseCategory.CourseCategoryEntity;
import jazdaz.JazdaZ.database.course.courseCategory.CourseCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CourseCategoryValidator {

    private final CourseCategoryRepository courseCategoryRepository;

    public boolean checkIfSameCategory(UUID id, CourseCategoryEntity courseCategory) {
        Optional<CourseCategoryEntity> foundCategoryByName = courseCategoryRepository.findByName(courseCategory.getName());
        Optional<CourseCategoryEntity> foundCategoryByDrivingLicenceCategory = courseCategoryRepository.findByDrivingLicenceCategory(courseCategory.getDrivingLicenceCategory());
        boolean isSameCategoryByName = foundCategoryByName.isPresent() && foundCategoryByName.get().getId().equals(id);
        boolean isSameCategoryByDrivingLicenceCategory = foundCategoryByDrivingLicenceCategory.isPresent() && foundCategoryByDrivingLicenceCategory.get().getId().equals(id);
        return isSameCategoryByName || isSameCategoryByDrivingLicenceCategory;
    }

    public void validateCourseCategory(CourseCategoryEntity courseCategory, boolean isSameCategory) {
        List<String> validationErrors = new ArrayList<>();

        validateName(courseCategory, validationErrors);
        validateDrivingLicenceCategory(courseCategory, validationErrors);
        if (!isSameCategory) {
            validateUniqueName(courseCategory, validationErrors);
            validateUniqueDrivingLicenceCategory(courseCategory, validationErrors);
        }

        if (!validationErrors.isEmpty()) {
            String errorMessage = String.join("\n", validationErrors);
            throw new ValidationException(errorMessage);
        }
    }

    private void validateName(CourseCategoryEntity courseCategory, List<String> validationErrors) {
        if (courseCategory.getName() == null || courseCategory.getName().isEmpty()) {
            validationErrors.add("Nazwa kategorii kursu nie może być pusta");
        }
    }

    private void validateDrivingLicenceCategory(CourseCategoryEntity courseCategory, List<String> validationErrors) {
        if (courseCategory.getDrivingLicenceCategory() == null || courseCategory.getDrivingLicenceCategory().isEmpty()) {
            validationErrors.add("Kategoria prawa jazdy nie może być pusta");
        }
    }

    private void validateUniqueName(CourseCategoryEntity courseCategory, List<String> validationErrors) {
        if (courseCategoryRepository.findByName(courseCategory.getName()).isPresent()) {
            validationErrors.add("Kategoria kursu o podanej nazwie już istnieje");
        }
    }

    private void validateUniqueDrivingLicenceCategory(CourseCategoryEntity courseCategory, List<String> validationErrors) {
        if (courseCategoryRepository.findByDrivingLicenceCategory(courseCategory.getDrivingLicenceCategory()).isPresent()) {
            validationErrors.add("Kategoria kursu o podanej kategorii prawa jazdy już istnieje");
        }
    }
}
