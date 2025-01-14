package task.tracker.backend.dto;

import java.util.List;

public record ErrorDto(
        int status,
        String error,
        List<String> messages,
        String path
) {
}
