package com.pavelshapel.randomizer.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class Utilities {
    public Class<?> getSuperClassGenericType(Object object, int index) {
        try {
            final ParameterizedType genericSuperclass = (ParameterizedType) object.getClass().getGenericSuperclass();
            return (Class<?>) genericSuperclass.getActualTypeArguments()[index];
        } catch (Exception exception) {
            return Object.class;
        }
    }

    public <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }
}
