package task.tracker.backend.dto;

import jakarta.validation.constraints.NotNull;

public record TaskUpdateDto(
        String title,
        String description,
        @NotNull(message = "Completed is required")
        boolean completed
) {
}
