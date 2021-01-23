package com.pavelshapel.randomizer.service.randomizer.collection;

import com.pavelshapel.randomizer.service.randomizer.Randomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.Range;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;

@Log4j2
public abstract class CollectionRandomizer<T> implements Randomizer<Collection<T>> {
    private final PrimitiveRandomizer<T> primitiveRandomizer;

    protected CollectionRandomizer(PrimitiveRandomizer<T> primitiveRandomizer) {
        this.primitiveRandomizer = primitiveRandomizer;
    }

    @Override
    public Collection<T> randomize() {
        return randomize(DEFAULT_POSITIVE_BYTE_RANGE.getValue());
    }

    @Override
    public Collection<T> randomize(Range<Long> range) {
        try {
            return implementRandomization(range);
        } catch (Exception exception) {
            log.error(RANDOMIZE_BY_DEFAULT, exception.toString());
            return randomize();
        }
    }

    private List<T> implementRandomization(Range<Long> range) {
        final Range<Long> intersectionWithPositiveByteRange =
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().intersectionWith(range);

        final long collectionSize = ThreadLocalRandom.current().nextLong(
                intersectionWithPositiveByteRange.getMinimum(),
                intersectionWithPositiveByteRange.getMaximum()
        );

        return Stream.generate(primitiveRandomizer::randomize)
                .limit(collectionSize)
                .collect(Collectors.toList());
    }
}
