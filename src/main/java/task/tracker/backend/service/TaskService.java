package task.tracker.backend.service;

import task.tracker.backend.dto.TaskDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskDto> getTasks();

    TaskDto createTask(TaskDto taskDto);

    TaskDto updateTask(UUID id, TaskDto taskDto);

    void deleteTask(UUID id);

}
