package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.entity.RandomEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Range;

import java.lang.reflect.ParameterizedType;

@Slf4j
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

    public RandomEntity<T> getRandomEntity(long min, long max) {
        return getRandomEntity(Range.between(min, max));
    }

    public RandomEntity<T> getRandomEntity(Range<Long> range) {
        return new RandomEntity<>(randomize(range), genericParameterClass);
    }

    public RandomEntity<T> getRandomEntity() {
        return new RandomEntity<>(randomize(), genericParameterClass);
    }

    public abstract T randomize();

    protected abstract T randomizeRange(Range<Long> range);
}
