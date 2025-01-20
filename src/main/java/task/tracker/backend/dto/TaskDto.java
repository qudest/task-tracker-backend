package task.tracker.backend.dto;

import java.time.Instant;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        boolean completed,
        Instant completedAt
) {
}
