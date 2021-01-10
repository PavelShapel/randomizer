package com.pavelshapel.randomizer.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.ParameterizedType;

@Getter
@FieldDefaults(
        makeFinal = true,
        level = AccessLevel.PRIVATE
)
public abstract class Randomizer<T> {
    Class<?> genericParameterClass = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public abstract T randomize();
}
