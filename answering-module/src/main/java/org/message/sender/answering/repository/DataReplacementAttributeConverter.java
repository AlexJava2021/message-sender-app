package org.message.sender.answering.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Converter
@Slf4j
public class DataReplacementAttributeConverter implements AttributeConverter<Map<String, String>, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, String> address) {
        try {
            return objectMapper.writeValueAsString(address);
        } catch (JsonProcessingException jpe) {
            log.warn("Cannot convert Address into JSON");
            return null;
        }
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String value) {
        try {
            return objectMapper.readValue(value, Map.class);
        } catch (JsonProcessingException e) {
            log.warn("Cannot convert JSON into Address");
            return null;
        }
    }
}
