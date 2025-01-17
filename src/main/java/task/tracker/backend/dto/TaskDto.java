package task.tracker.backend.dto;

import task.tracker.backend.model.Task;

import java.time.Instant;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        boolean isCompleted,
        Instant completedAt
) {

    public TaskDto(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted(), task.getCompletedAt());
    }

}
