package jazdaz.JazdaZ.validator;

import jazdaz.JazdaZ.database.lesson.LessonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonValidator {

    public void validateLesson(LessonEntity lesson) {
        List<String> validationErrors = new ArrayList<>();

        validateType(lesson, validationErrors);
        validateHours_spend(lesson, validationErrors);

        if (!validationErrors.isEmpty()) {
            String errorMessage = String.join("", validationErrors);
            throw new ValidationException(errorMessage);
        }
    }

    private void validateType(LessonEntity lesson, List<String> validationErrors) {
        if (lesson.getType() == null || lesson.getType().isEmpty()) {
            validationErrors.add("Typ lekcji nie może być pusty\n");
        }
    }

    private void validateHours_spend(LessonEntity lesson, List<String> validationErrors) {
        if (lesson.getHours_spend() < 0) {
            validationErrors.add("Ilość godzin nie może być mniejsza od 0\n");
        }
    }
}
