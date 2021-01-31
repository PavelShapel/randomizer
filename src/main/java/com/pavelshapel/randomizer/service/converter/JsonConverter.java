package com.pavelshapel.randomizer.service.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class JsonConverter {
    private final ObjectMapper objectMapper;

    @Autowired
    public JsonConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Optional<String> pojoToJson(Object object) {
        try {
            final String json = objectMapper.writeValueAsString(object);
            return isValidJson(json) ? Optional.ofNullable(json) : Optional.empty();
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    public <T> Optional<T> jsonToPojo(String json, Class<T> targetClass) {
        try {
            return isValidJson(json) ? Optional.ofNullable(objectMapper.readValue(json, targetClass)) : Optional.empty();
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    public Optional<Map<String, Object>> pojoToMap(Object object) {
        try {
            return Optional.ofNullable(objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {
            }));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    public <T> Optional<T> mapToPojo(Map<String, Object> map, Class<T> targetClass) {
        try {
            return Optional.ofNullable(objectMapper.convertValue(map, targetClass));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    public boolean isValidJson(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException externalException) {
            try {
                new JSONArray(json);
            } catch (JSONException internalException) {
                return false;
            }
        }
        return true;
    }
}