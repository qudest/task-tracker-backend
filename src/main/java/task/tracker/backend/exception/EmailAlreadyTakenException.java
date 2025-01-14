package task.tracker.backend.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyTakenException extends AbstractException {

    public EmailAlreadyTakenException() {
        super(HttpStatus.CONFLICT.value(), "This email is already taken");
    }

}
