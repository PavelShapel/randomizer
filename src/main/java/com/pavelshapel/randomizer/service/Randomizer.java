package com.pavelshapel.randomizer.service;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Range;

import java.lang.reflect.ParameterizedType;

@Slf4j
@Getter
@FieldDefaults(
        makeFinal = true,
        level = AccessLevel.PRIVATE
)
@ToString
@EqualsAndHashCode
public abstract class Randomizer<T> {
    Class<?> genericParameterClass = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public T randomize(Range<Long> range) {
        try {
            return randomizeRange(range);
        } catch (Exception e) {
            log.error("randomize by default, because an exception is thrown [{}]", e.toString());
            return randomize();
        }
    }

    public abstract T randomize();

    protected abstract T randomizeRange(Range<Long> range);
}
