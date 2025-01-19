package task.tracker.backend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import task.tracker.backend.dto.TaskDto;
import task.tracker.backend.exception.TaskNotFoundException;
import task.tracker.backend.mapper.TaskMapper;
import task.tracker.backend.model.Task;
import task.tracker.backend.model.User;
import task.tracker.backend.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TaskService {

    TaskRepository taskRepository;
    TaskMapper mapper = TaskMapper.INSTANCE;

    public List<TaskDto> getTasks() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskRepository.findAllByUser(user).stream().map(mapper::toDto).toList();
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task task = mapper.toEntity(taskDto);
        task.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        task.setCompleted(false);
        Task saved = taskRepository.save(task);
        return mapper.toDto(saved);
    }

    public TaskDto updateTask(UUID id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        if (!task.getUser().equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            throw new AccessDeniedException("You can't update this task");
        }
        Task updatingTask = mapper.toEntity(taskDto);
        updatingTask.setUser(task.getUser());
        Task saved = taskRepository.save(updatingTask);
        return mapper.toDto(saved);
    }

    public void deleteTask(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        if (!task.getUser().equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            throw new AccessDeniedException("You can't delete this task");
        }
        taskRepository.delete(task);
    }

}