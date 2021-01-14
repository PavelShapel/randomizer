package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.entity.RandomEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Range;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;

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

    public List<RandomEntity<T>> getRandomEntityList() {
        final long collectionSize = ThreadLocalRandom.current().nextLong(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );

        return Stream.generate(this::getRandomEntity)
                .limit(collectionSize)
                .collect(Collectors.toList());
    }

    public abstract T randomize();

    protected abstract T randomizeRange(Range<Long> range);
}
