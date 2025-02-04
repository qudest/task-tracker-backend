package task.tracker.backend.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.tracker.backend.dto.TaskCreationDto;
import task.tracker.backend.dto.TaskDto;
import task.tracker.backend.dto.TaskUpdateDto;
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
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskCreationDto taskCreationDto) {
        return ResponseEntity.ok(taskService.createTask(taskCreationDto));
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable UUID id, @Valid @RequestBody TaskUpdateDto taskUpdateDto) {
        return ResponseEntity.ok(taskService.updateTask(id, taskUpdateDto));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
