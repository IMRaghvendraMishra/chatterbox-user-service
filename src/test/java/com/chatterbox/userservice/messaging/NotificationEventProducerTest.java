package com.chatterbox.userservice.messaging;

import com.chatterbox.userservice.util.ObjectJsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.mock;

class NotificationEventProducerTest {

    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectJsonMapper mapper;

    @InjectMocks
    private NotificationEventProducer producer;

    @Captor
    ArgumentCaptor<String> jsonCaptor;

    @BeforeEach
    void setUp() {
        kafkaTemplate = mock(KafkaTemplate.class);
        mapper = mock(ObjectJsonMapper.class);
        // Manually inject the topic value if needed
        producer.notificationEventsTopicName = "test-topic";
    }

    // TODO: Fix it
    /*@Test
    void sendNotificationEvent_shouldConvertToJsonAndSendToKafka() {
        // Arrange
        String username = "john_doe";
        String message = "Welcome!";
        String fakeJson = "{\"username\":\"john_doe\",\"message\":\"Welcome!\"}";

        when(mapper.toJson(any())).thenReturn(fakeJson);

        // Act
        producer.sendNotificationEvent(username, message);

        // Assert
        verify(mapper).toJson(argThat(obj ->
                obj.toString().contains("john_doe") && obj.toString().contains("Welcome!")
        ));
        verify(kafkaTemplate).send(eq("test-topic"), eq(fakeJson));
    }*/
}
