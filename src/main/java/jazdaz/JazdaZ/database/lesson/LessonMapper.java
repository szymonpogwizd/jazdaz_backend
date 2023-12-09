package jazdaz.JazdaZ.database.lesson;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface LessonMapper {

    LessonInfoDTO lessonEntity2LessonInfoDTO(LessonEntity lessonEntity);

    LessonEntity lessonCreateDTO2LessonEntity(LessonCreateDTO lessonCreateDTO);

    LessonEntity lessonUpdateDTO2LessonEntity(LessonUpdateDTO lessonUpdateDTO);
}
