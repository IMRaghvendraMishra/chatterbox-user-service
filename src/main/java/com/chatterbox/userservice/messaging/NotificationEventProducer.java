package com.chatterbox.userservice.messaging;

import com.chatterbox.userservice.util.ObjectJsonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Sends notification events to a Kafka topic to notify users.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationEventProducer {

    @Autowired private final ObjectJsonMapper mapper;

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${spring.kafka.notification-events-topic-name}")
    String notificationEventsTopicName;

    public void sendNotificationEvent(String username, String message) {
        var payload = mapper.toJson(new NotificationEvent(username, message));
        kafkaTemplate.send(notificationEventsTopicName, payload);
        log.info("A new notification sent to with payload {}", payload);
    }

    private record NotificationEvent(String username, String message) {}
}
