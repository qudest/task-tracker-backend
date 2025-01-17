package task.tracker.backend.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.tracker.backend.dto.TaskDto;
import task.tracker.backend.service.TaskService;

import java.util.List;
import java.util.UUID;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TaskController {

    TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.createTask(taskDto));
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable UUID id, @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.updateTask(id, taskDto));
    }

}
