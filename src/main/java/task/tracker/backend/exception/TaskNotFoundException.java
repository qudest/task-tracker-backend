package task.tracker.backend.exception;

import org.springframework.http.HttpStatus;

public class TaskNotFoundException extends AbstractException {

    public TaskNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Task not found");
    }

}
