package task.tracker.backend.kafka.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import task.tracker.backend.kafka.event.Event;

import java.util.Map;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProducerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    String bootstrapServers;

    @Value("${spring.kafka.producer.acks}")
    String acks;

    @Value("${spring.kafka.producer.properties.enable.idempotence}")
    String enableIdempotence;

    @Value("${spring.kafka.producer.properties.delivery.timeout.ms}")
    String deliveryTimeoutMs;

    @Value("${spring.kafka.producer.properties.linger.ms}")
    String lingerMs;

    @Value("${spring.kafka.producer.properties.request.timeout.ms}")
    String requestTimeoutMs;

    private Map<String, Object> producerConfigs() {
        return Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class,
                ProducerConfig.ACKS_CONFIG, acks,
                ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempotence,
                ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeoutMs,
                ProducerConfig.LINGER_MS_CONFIG, lingerMs,
                ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeoutMs
        );
    }

    @Bean
    public ProducerFactory<String, Event> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Event> kafkaTemplate() {
        KafkaTemplate<String, Event> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setDefaultTopic(TopicConfiguration.TOPIC_NAME);
        return kafkaTemplate;
    }

}
