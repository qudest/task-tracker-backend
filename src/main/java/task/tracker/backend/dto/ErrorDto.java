package task.tracker.backend.dto;

public record ErrorDto(
        int status,
        String error,
        String message,
        String path
) {
}
