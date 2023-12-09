package jazdaz.JazdaZ.service;

import jazdaz.JazdaZ.database.course.courseCategory.CourseCategoryEntity;
import jazdaz.JazdaZ.database.course.courseCategory.CourseCategoryRepository;
import jazdaz.JazdaZ.validator.CourseCategoryValidator;
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
public class CourseCategoryService {

    private final CourseCategoryRepository courseCategoryRepository;
    private final CourseCategoryValidator courseCategoryValidator;

    @Transactional
    public CourseCategoryEntity create(CourseCategoryEntity courseCategory) {
        log.debug("Creating course category {}", courseCategory);
        courseCategoryValidator.validateCourseCategory(courseCategory, false);
        return log.traceExit(courseCategoryRepository.save(courseCategory));
    }

    @Transactional
    public CourseCategoryEntity update(UUID id, CourseCategoryEntity courseCategory) {
        log.debug("Updating course category {}: {}", id, courseCategory);
        boolean isSameCategory = courseCategoryValidator.checkIfSameCategory(id, courseCategory);
        courseCategoryValidator.validateCourseCategory(courseCategory, isSameCategory);
        CourseCategoryEntity toUpdate = courseCategoryRepository.findById(id).orElseThrow(() -> new ValidationException("Kategoria kursu o podanym id nie istnieje"));
        toUpdate.setName(courseCategory.getName());
        return log.traceExit(courseCategoryRepository.save(toUpdate));
    }

    public void delete(UUID id) {
        log.debug("Deleting course category {}", id);
        courseCategoryRepository.deleteById(id);
    }

    public List<CourseCategoryEntity> getAll() {
        log.debug("Getting all course categories");
        return log.traceExit(courseCategoryRepository.findAll());
    }
}
