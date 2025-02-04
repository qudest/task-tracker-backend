package task.tracker.backend.service;

import task.tracker.backend.dto.TaskCreationDto;
import task.tracker.backend.dto.TaskDto;
import task.tracker.backend.dto.TaskUpdateDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskDto> getTasks();

    TaskDto createTask(TaskCreationDto taskDto);

    TaskDto updateTask(UUID id, TaskUpdateDto taskDto);

    void deleteTask(UUID id);

}
