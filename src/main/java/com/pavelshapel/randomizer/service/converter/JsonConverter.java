package com.pavelshapel.randomizer.service.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Map;

@Service
public class JsonConverter {
    @SneakyThrows
    public String pojoToJson(Object object) {
        return getObjectMapper().writeValueAsString(object);
    }

    @SneakyThrows
    public <T> T jsonToPojo(String json, Class<T> targetClass) {
        return getObjectMapper().readValue(json, targetClass);
    }

    public Map<String, Object> pojoToMap(Object object) {
        return getObjectMapper().convertValue(object, new TypeReference<Map<String, Object>>() {
        });
    }

    public <T> T mapToPojo(Map<String, Object> map, Class<T> targetClass) {
        return getObjectMapper().convertValue(map, targetClass);
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy hh:mm"));

        return mapper;
    }


}