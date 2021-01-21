package com.pavelshapel.randomizer.service.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class JsonConverter {
    private ObjectMapper objectMapper;

    @SneakyThrows
    public String pojoToJson(Object object) {
        return objectMapper.writeValueAsString(object);
    }

    @SneakyThrows
    public <T> T jsonToPojo(String json, Class<T> targetClass) {
        return objectMapper.readValue(json, targetClass);
    }

    public Map<String, Object> pojoToMap(Object object) {
        return objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {
        });
    }

    public <T> T mapToPojo(Map<String, Object> map, Class<T> targetClass) {
        return objectMapper.convertValue(map, targetClass);
    }
}