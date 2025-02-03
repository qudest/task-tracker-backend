package task.tracker.backend.kafka.event;

public record EmailEvent(
        String email,
        String subject,
        String message
) implements Event {

    @Override
    public String getKey() {
        return email;
    }

}