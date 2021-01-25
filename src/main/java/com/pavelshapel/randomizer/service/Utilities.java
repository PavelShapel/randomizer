package com.pavelshapel.randomizer.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class Utilities {
    public Class<?> getSuperClassGenericType(Object object, int index) {
        final ParameterizedType genericSuperclass = (ParameterizedType) object.getClass().getGenericSuperclass();
        final Type actualTypeArgument = genericSuperclass.getActualTypeArguments()[index];
        return actualTypeArgument instanceof ParameterizedType
                ? (Class<?>) ((ParameterizedType) actualTypeArgument).getRawType()
                : (Class<?>) actualTypeArgument;
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
