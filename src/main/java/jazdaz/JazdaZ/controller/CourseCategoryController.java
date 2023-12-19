package jazdaz.JazdaZ.controller;

import jazdaz.JazdaZ.database.course.courseCategory.*;
import jazdaz.JazdaZ.service.CourseCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/coursesCategories")
@RequiredArgsConstructor
@CrossOrigin
public class CourseCategoryController {

    private final CourseCategoryService courseCategoryService;
    private final CourseCategoryMapper courseCategoryMapper;

    @PostMapping
    public CourseCategoryInfoDTO createCourseCategory(@RequestBody @Valid CourseCategoryCreateDTO courseCategory) {
        log.debug("Create course category {}", courseCategory);
        CourseCategoryEntity toCreate = courseCategoryMapper.courseCategoryCreateDTO2CourseCategoryEntity(courseCategory);
        CourseCategoryEntity createdCourseCategory = courseCategoryService.create(toCreate);
        return log.traceExit(courseCategoryMapper.courseCategoryEntity2CourseCategoryInfoDTO(createdCourseCategory));
    }

    @PutMapping("{id}")
    public CourseCategoryInfoDTO updateCourseCategory(@RequestBody @Valid CourseCategoryUpdateDTO courseCategory, @PathVariable UUID id) {
        log.debug("Update course category {}: {}", id, courseCategory);
        CourseCategoryEntity updatedCourseCategory = courseCategoryService.update(id, courseCategoryMapper.courseCategoryUpdateDTO2CourseCategoryEntity(courseCategory));
        return log.traceExit(courseCategoryMapper.courseCategoryEntity2CourseCategoryInfoDTO(updatedCourseCategory));
    }

    @GetMapping
    public List<CourseCategoryInfoDTO> getAll() {
        log.debug("Getting all course categories");
        return log.traceExit(
                courseCategoryService.getAll()
                        .stream()
                        .map(courseCategoryMapper::courseCategoryEntity2CourseCategoryInfoDTO)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("{id}")
    public CourseCategoryInfoDTO getCourseCategory(@PathVariable UUID id) {
        log.debug("Getting course category {}", id);
        return log.traceExit(
                courseCategoryService.getCourseCategory(id)
                        .map(courseCategoryMapper::courseCategoryEntity2CourseCategoryInfoDTO)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course category not found"))
        );
    }

    @DeleteMapping("{id}")
    public void deleteCourseCategory(@PathVariable UUID id) {
        log.debug("Deleting course category {}", id);
        courseCategoryService.delete(id);
    }
}
