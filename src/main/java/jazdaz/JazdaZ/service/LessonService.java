package jazdaz.JazdaZ.service;

import jazdaz.JazdaZ.database.lesson.LessonEntity;
import jazdaz.JazdaZ.database.lesson.LessonRepository;
import jazdaz.JazdaZ.validator.LessonValidator;
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
public class LessonService {

    private final LessonRepository lessonRepository;
    private final LessonValidator lessonValidator;

    @Transactional
    public LessonEntity create(LessonEntity lesson) {
        log.debug("Creating lesson {}", lesson);

        lessonValidator.validateLesson(lesson);

        return log.traceExit(lessonRepository.save(lesson));
    }

    @Transactional
    public LessonEntity update(UUID id, LessonEntity lesson) {
        log.debug("Editing lesson {} - {}", id, lesson);
        lessonValidator.validateLesson(lesson);
        LessonEntity toUpdate = lessonRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Lesson with id " + id + " was not found"));

        toUpdate.setType(lesson.getType());
        toUpdate.setHours_spend(lesson.getHours_spend());
        toUpdate.setEnd_date(lesson.getEnd_date());
        toUpdate.setCourse(lesson.getCourse());
        toUpdate.setVehicle(lesson.getVehicle());

        return log.traceExit(lessonRepository.save(toUpdate));
    }

    public void delete(UUID id) {
        log.debug("Deleting lesson {}", id);
        lessonRepository.deleteById(id);
    }

    public List<LessonEntity> getAll() {
        log.debug("Getting all lessons");
        return log.traceExit(lessonRepository.findAll());
    }

    public Optional<LessonEntity> getLesson(UUID id) {
        log.debug("Getting lesson {}", id);
        return log.traceExit(lessonRepository.findById(id));
    }
}
