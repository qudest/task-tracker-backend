package task.tracker.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import task.tracker.backend.dto.UserDto;
import task.tracker.backend.dto.UserInfoDto;
import task.tracker.backend.model.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserInfoDto toDto(User user);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDto);

}
