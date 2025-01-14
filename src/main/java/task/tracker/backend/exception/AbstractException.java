package task.tracker.backend.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractException extends RuntimeException {

    private final int status;

    public AbstractException(int status, String message) {
        super(message);
        this.status = status;
    }

}
