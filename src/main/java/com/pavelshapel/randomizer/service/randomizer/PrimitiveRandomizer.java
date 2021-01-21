package com.pavelshapel.randomizer.service.randomizer;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.Range;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;

@Log4j2
public abstract class PrimitiveRandomizer<T> implements Randomizer<T> {
    @Override
    public T randomize(Range<Long> range) {
        try {
            return randomizeRange(range);
        } catch (Exception e) {
            log.error("randomize by default, because an exception is thrown [{}]", e.toString());
            return randomize();
        }
    }

    @Override
    public Collection<T> randomizeCollection() {
        final long collectionSize = ThreadLocalRandom.current().nextLong(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );

        return Stream.generate(this::randomize)
                .limit(collectionSize)
                .collect(Collectors.toList());
    }

    protected abstract T randomizeRange(Range<Long> range);
}
