package task.tracker.backend.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import task.tracker.backend.dto.TaskDto;
import task.tracker.backend.service.TaskService;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TaskController {

    TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

}
