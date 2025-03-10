package task.tracker.backend.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.tracker.backend.dto.TaskCreationDto;
import task.tracker.backend.dto.TaskDto;
import task.tracker.backend.dto.TaskUpdateDto;
import task.tracker.backend.exception.TaskNotFoundException;
import task.tracker.backend.mapper.TaskMapper;
import task.tracker.backend.model.Task;
import task.tracker.backend.model.User;
import task.tracker.backend.repository.TaskRepository;
import task.tracker.backend.service.TaskService;

import java.util.List;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;
    TaskMapper mapper = TaskMapper.INSTANCE;

    public List<TaskDto> getTasks() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskRepository.findAllByUser(user).stream().map(mapper::toDto).toList();
    }

    public TaskDto createTask(TaskCreationDto taskCreationDto) {
        Task task = mapper.toEntity(taskCreationDto);
        task.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        task.setCompleted(false);
        Task saved = taskRepository.save(task);
        return mapper.toDto(saved);
    }

    @Transactional
    public TaskDto updateTask(UUID id, TaskUpdateDto taskUpdateDto) {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        if (!task.getUser().getId().equals(
                ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()
        )) {
            throw new AccessDeniedException("You can't update this task");
        }
        Task updatingTask = mapper.toEntity(taskUpdateDto);
        updatingTask.setId(id);
        updatingTask.setUser(task.getUser());
        Task saved = taskRepository.save(updatingTask);
        return mapper.toDto(saved);
    }

    @Transactional
    public void deleteTask(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        if (!task.getUser().getId().equals(
                ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()
        )) {
            throw new AccessDeniedException("You can't delete this task");
        }
        taskRepository.delete(task);
    }

}