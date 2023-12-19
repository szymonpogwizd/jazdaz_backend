package jazdaz.JazdaZ.controller;

import jazdaz.JazdaZ.database.lesson.*;
import jazdaz.JazdaZ.service.LessonService;
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
@RequestMapping("/lessons")
@RequiredArgsConstructor
@CrossOrigin
public class LessonController {

    private final LessonService lessonService;
    private final LessonMapper lessonMapper;

    @PostMapping
    public LessonInfoDTO createLesson(@RequestBody @Valid LessonCreateDTO lesson) {
        log.debug("Create lesson {}", lesson);
        LessonEntity toCreate = lessonMapper.lessonCreateDTO2LessonEntity(lesson);
        LessonEntity createdLesson = lessonService.create(toCreate);
        return log.traceExit(lessonMapper.lessonEntity2LessonInfoDTO(createdLesson));
    }

    @PutMapping("{id}")
    public LessonInfoDTO updateLesson(@RequestBody @Valid LessonUpdateDTO lesson, @PathVariable UUID id) {
        log.debug("Update lesson {}: {}", id, lesson);
        LessonEntity updatedLesson = lessonService.update(id, lessonMapper.lessonUpdateDTO2LessonEntity(lesson));
        return log.traceExit(lessonMapper.lessonEntity2LessonInfoDTO(updatedLesson));
    }

    @GetMapping
    public List<LessonInfoDTO> getAll() {
        log.debug("Getting all lessons");
        return log.traceExit(
                lessonService.getAll()
                        .stream()
                        .map(lessonMapper::lessonEntity2LessonInfoDTO)
                        .collect(java.util.stream.Collectors.toList())
        );
    }

    @GetMapping("{id}")
    public LessonInfoDTO getLesson(@PathVariable UUID id) {
        log.debug("Getting lesson {}", id);
        return log.traceExit(
                lessonService.getLesson(id)
                        .map(lessonMapper::lessonEntity2LessonInfoDTO)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found"))
        );
    }

    @DeleteMapping("{id}")
    public void deleteLesson(@PathVariable UUID id) {
        log.debug("Deleting lesson {}", id);
        lessonService.delete(id);
    }
}
