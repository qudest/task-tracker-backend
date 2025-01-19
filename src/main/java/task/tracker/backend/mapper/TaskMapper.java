package task.tracker.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import task.tracker.backend.dto.TaskDto;
import task.tracker.backend.model.Task;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto toDto(Task task);

    Task toEntity(TaskDto taskDto);

}
