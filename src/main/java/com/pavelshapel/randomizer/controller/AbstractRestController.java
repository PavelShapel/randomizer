package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.service.Randomizer;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public abstract class AbstractRestController<T> {
    public static final String PATH_GENERIC_RANGE = "/{min:[\\d]+}/{max:[\\d]+}";

    @Autowired
    private Randomizer<T> randomizer;

    protected RandomEntity<T> getRandomEntity() {
        final T value = randomizer.randomize();
        final Class<?> type = randomizer.getGenericParameterClass();

        return RandomEntity.<T>builder().value(value).type(type).build();
    }

    protected RandomEntity<T> getRandomEntity(long min, long max) {
        final T value = randomizer.randomize(Range.between(min, max));
        final Class<?> type = randomizer.getGenericParameterClass();

        return RandomEntity.<T>builder().value(value).type(type).build();
    }

    protected abstract ResponseEntity<RandomEntity<T>> randomize();

    protected abstract ResponseEntity<RandomEntity<T>> randomize(long min, long max);
}