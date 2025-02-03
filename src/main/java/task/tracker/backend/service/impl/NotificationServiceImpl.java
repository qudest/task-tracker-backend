package task.tracker.backend.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import task.tracker.backend.kafka.config.TopicConfiguration;
import task.tracker.backend.kafka.event.EmailEvent;
import task.tracker.backend.kafka.event.Event;
import task.tracker.backend.service.NotificationService;
import task.tracker.backend.templates.EmailTemplates;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    KafkaTemplate<String, Event> kafkaProducer;

    public void sendWelcomeEmail(String email) {
        Event emailEvent = new EmailEvent(
                email,
                EmailTemplates.WELCOME_EMAIL_SUBJECT,
                String.format(EmailTemplates.WELCOME_EMAIL_MESSAGE, email)
        );
        kafkaProducer.send(TopicConfiguration.TOPIC_NAME, emailEvent.getKey(), emailEvent);
    }

}
