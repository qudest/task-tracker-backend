package task.tracker.backend.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AbstractException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "User not found");
    }

}
