package jazdaz.JazdaZ.database.course;

import jazdaz.JazdaZ.database.course.courseCategory.CourseCategoryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        uses = {CourseCategoryMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CourseMapper {

    CourseInfoDTO courseEntity2CourseInfoDTO(CourseEntity courseEntity);
    CourseEntity courseCreateDTO2CourseEntity(CourseCreateDTO courseCreateDTO);
    CourseEntity courseUpdateDTO2CourseEntity(CourseUpdateDTO courseUpdateDTO);
}
