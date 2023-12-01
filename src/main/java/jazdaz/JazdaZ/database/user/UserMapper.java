package jazdaz.JazdaZ.database.user;

import jazdaz.JazdaZ.utils.password.EncodedMapping;
import jazdaz.JazdaZ.utils.password.PasswordEncoderMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        uses = PasswordEncoderMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserInfoDTO userDAO2UserInfoDTO(UserEntity userDAO);

    @Mapping(target = "password", qualifiedBy = EncodedMapping.class)
    UserEntity userCreateDTO2UserDAO(UserCreateDTO userCreateDTO);

    UserEntity userUpdateDTO2UserDAO(UserUpdateDTO userUpdateDTO);
}

