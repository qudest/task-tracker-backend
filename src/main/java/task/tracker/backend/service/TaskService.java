package task.tracker.backend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import task.tracker.backend.dto.TaskDto;
import task.tracker.backend.model.User;
import task.tracker.backend.repository.TaskRepository;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TaskService {

    TaskRepository taskRepository;

    public List<TaskDto> getTasks() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskRepository.findAllByUser(user).stream().map(TaskDto::new).toList();
    }

}