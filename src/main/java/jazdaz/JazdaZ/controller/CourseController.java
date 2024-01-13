package jazdaz.JazdaZ.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jazdaz.JazdaZ.database.course.*;
import jazdaz.JazdaZ.database.course.courseCategory.CourseCategoryRepository;
import jazdaz.JazdaZ.service.CourseService;
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
@RequestMapping("/courses")
@RequiredArgsConstructor
@CrossOrigin
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final CourseCategoryRepository courseCategoryRepository;

    @PostMapping
    public CourseInfoDTO createCourse(@RequestBody @Valid CourseCreateDTO course) {
        log.debug("Create course {}", course);
        CourseEntity toCreate = courseMapper.courseCreateDTO2CourseEntity(course);
        courseCategoryRepository.findById(course.getCourseCategoryId()).ifPresent(toCreate::setCourseCategory);
        CourseEntity createdCourse = courseService.create(toCreate);
        return log.traceExit(courseMapper.courseEntity2CourseInfoDTO(createdCourse));
    }

    @PutMapping("{id}")
    public CourseInfoDTO updateCourse(@RequestBody @Valid CourseUpdateDTO course, @PathVariable UUID id) {
        log.debug("Update course {}: {}", id, course);
        CourseEntity toUpdate = courseMapper.courseUpdateDTO2CourseEntity(course);
        courseCategoryRepository.findById(course.getCourseCategoryId()).ifPresent(toUpdate::setCourseCategory);
        CourseEntity updatedCourse = courseService.update(id, toUpdate);
        return log.traceExit(courseMapper.courseEntity2CourseInfoDTO(updatedCourse));
    }

    @GetMapping
    public List<CourseInfoDTO> getAll() {
        log.debug("Getting all courses");
        return log.traceExit(
                courseService.getAll()
                        .stream()
                        .map(courseMapper::courseEntity2CourseInfoDTO)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("{id}")
    public CourseInfoDTO getCourse(@PathVariable UUID id) {
        log.debug("Getting course {}", id);
        return log.traceExit(
                courseService.getCourse(id)
                        .map(courseMapper::courseEntity2CourseInfoDTO)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"))
        );
    }

    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable UUID id) {
        log.debug("Deleting course {}", id);
        courseService.delete(id);
    }
}
