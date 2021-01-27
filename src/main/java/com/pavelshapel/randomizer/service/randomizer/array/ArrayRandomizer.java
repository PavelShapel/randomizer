package com.pavelshapel.randomizer.service.randomizer.array;

import com.pavelshapel.randomizer.entity.Entity;
import com.pavelshapel.randomizer.service.randomizer.EntityRandomizer;
import com.pavelshapel.randomizer.service.randomizer.Randomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;

@Log4j2
@Getter
@RequiredArgsConstructor
public abstract class ArrayRandomizer<T> implements Randomizer<T[]> {
    private final Class<T[]> genericClass;
    @Autowired
    private PrimitiveRandomizer<T> primitiveRandomizer;

    @Override
    public T[] randomize() {
        return randomize(null, null);
    }

    @Override
    public T[] randomize(Range<Long> range) {
        return randomize(null, range);
    }

    public T[] randomize(Object valueForSupplier, Range<Long> arrayLengthRange) {
        try {
            return implementRandomization(valueForSupplier, arrayLengthRange);
        } catch (Exception exception) {
            log.error(RANDOMIZE_BY_DEFAULT, exception.toString());
            return randomize();
        }
    }

    private T[] implementRandomization(Object valueForSupplier, Range<Long> arrayLengthRange) {
        final long arrayLength = getArrayLength(arrayLengthRange);
        final Supplier<T> supplier = getSupplier(valueForSupplier);
        final List<T> randomCollection = Stream.generate(supplier)
                .limit(arrayLength)
                .collect(Collectors.toList());

        return collectionToArray(randomCollection);
    }

    protected abstract T[] collectionToArray(Collection<T> collection);

    private long getArrayLength(Range<Long> range) {
        final Range<Long> intersectionWithPositiveByteRange =
                Objects.isNull(range)
                        ? DEFAULT_POSITIVE_BYTE_RANGE.getValue()
                        : DEFAULT_POSITIVE_BYTE_RANGE.getValue().intersectionWith(range);

        return ThreadLocalRandom.current().nextLong(
                intersectionWithPositiveByteRange.getMinimum(),
                intersectionWithPositiveByteRange.getMaximum()
        );
    }

    private Supplier<T> getSupplier(Object valueForSupplier) {
        if (Objects.isNull(valueForSupplier)) {
            return primitiveRandomizer::randomize;
        }

        if (valueForSupplier instanceof Entity) {
            return () -> ((EntityRandomizer<T>) primitiveRandomizer).randomize((Entity) valueForSupplier);
        } else {
            return () -> primitiveRandomizer.randomize((Range<Long>) valueForSupplier);
        }
    }
}
