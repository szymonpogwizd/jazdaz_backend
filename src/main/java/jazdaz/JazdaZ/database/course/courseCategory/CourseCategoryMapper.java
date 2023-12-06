package jazdaz.JazdaZ.database.course.courseCategory;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface CourseCategoryMapper {

    CourseCategoryInfoDTO courseCategoryEntity2CourseCategoryInfoDTO(CourseCategoryEntity courseCategoryEntity);

    CourseCategoryEntity courseCategoryCreateDTO2CourseCategoryEntity(CourseCategoryCreateDTO courseCategoryCreateDTO);

    CourseCategoryEntity courseCategoryUpdateDTO2CourseCategoryEntity(CourseCategoryUpdateDTO courseCategoryUpdateDTO);
}
