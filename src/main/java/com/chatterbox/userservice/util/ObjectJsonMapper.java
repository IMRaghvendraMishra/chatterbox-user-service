package com.chatterbox.userservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Utility class for serializing and deserializing JSON using Jackson.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ObjectJsonMapper {

    private final ObjectMapper objectMapper;

    public String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize object: {}", obj, e);
            return "{}";
        }
    }
}
