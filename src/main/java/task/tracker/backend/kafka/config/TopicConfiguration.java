package task.tracker.backend.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

// Topic creation should be done at the DevOps infrastructure level, but I implemented it this way for simplicity.
@Configuration
public class TopicConfiguration {

    public static final String TOPIC_NAME = "EMAIL_SENDING_TASKS";

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder
                .name(TOPIC_NAME)
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }

}
