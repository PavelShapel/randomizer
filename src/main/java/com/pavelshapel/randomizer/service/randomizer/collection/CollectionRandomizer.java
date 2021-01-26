package com.pavelshapel.randomizer.service.randomizer.collection;

import com.pavelshapel.randomizer.service.randomizer.Randomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import com.pavelshapel.randomizer.service.randomizer.MapRandomizer;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;

@Log4j2
public abstract class CollectionRandomizer<T> implements Randomizer<Collection<T>> {
    @Autowired
    private PrimitiveRandomizer<T> primitiveRandomizer;

    @Override
    public Collection<T> randomize() {
        return randomize(null, null);
    }

    @Override
    public Collection<T> randomize(Range<Long> range) {
        return randomize(null, range);
    }

    public Collection<T> randomize(Object valueForSupplier, Range<Long> collectionSizeRange) {
        try {
            return implementRandomization(valueForSupplier, collectionSizeRange);
        } catch (Exception exception) {
            log.error(RANDOMIZE_BY_DEFAULT, exception.toString());
            return randomize();
        }
    }

    private List<T> implementRandomization(Object valueForSupplier, Range<Long> collectionSizeRange) {
        final long collectionSize = getCollectionSize(collectionSizeRange);
        final Supplier<T> randomize = getSupplier(valueForSupplier);

        return Stream.generate(randomize)
                .limit(collectionSize)
                .collect(Collectors.toList());
    }

    private long getCollectionSize(Range<Long> range) {
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

        if (primitiveRandomizer instanceof MapRandomizer && valueForSupplier instanceof Map) {
            return () -> ((MapRandomizer<T>) primitiveRandomizer).randomize((Map<String, Object>) valueForSupplier);
        } else {
            return () -> primitiveRandomizer.randomize((Range<Long>) valueForSupplier);
        }
    }
}
