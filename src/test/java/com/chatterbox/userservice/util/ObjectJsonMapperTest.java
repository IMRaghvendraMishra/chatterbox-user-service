package com.chatterbox.userservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ObjectJsonMapperTest {

    private ObjectMapper mockObjectMapper;
    private ObjectJsonMapper objectJsonMapper;

    @BeforeEach
    void setUp() {
        mockObjectMapper = Mockito.mock(ObjectMapper.class);
        objectJsonMapper = new ObjectJsonMapper(mockObjectMapper);
    }

    @Test
    void toJson_shouldReturnJsonString_whenSerializationIsSuccessful() throws JsonProcessingException {
        // Given
        TestObject testObj = new TestObject("test", 123);
        String expectedJson = "{\"name\":\"test\",\"value\":123}";

        when(mockObjectMapper.writeValueAsString(testObj)).thenReturn(expectedJson);

        // When
        String result = objectJsonMapper.toJson(testObj);

        // Then
        assertEquals(expectedJson, result);
        verify(mockObjectMapper, times(1)).writeValueAsString(testObj);
    }

    @Test
    void toJson_shouldReturnEmptyJson_whenSerializationFails() throws JsonProcessingException {
        // Given
        TestObject testObj = new TestObject("error", 999);

        when(mockObjectMapper.writeValueAsString(testObj)).thenThrow(new JsonProcessingException("error") {});

        // When
        String result = objectJsonMapper.toJson(testObj);

        // Then
        assertEquals("{}", result);
        verify(mockObjectMapper, times(1)).writeValueAsString(testObj);
    }

    // Simple class used for testing
    static class TestObject {
        private String name;
        private int value;

        public TestObject(String name, int value) {
            this.name = name;
            this.value = value;
        }

        // Getters and setters (optional if using public fields or Lombok)
        public String getName() { return name; }
        public int getValue() { return value; }
    }
}
