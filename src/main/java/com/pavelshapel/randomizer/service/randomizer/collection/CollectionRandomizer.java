package com.pavelshapel.randomizer.service.randomizer.collection;

import com.pavelshapel.randomizer.service.randomizer.Randomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.Range;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
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
        return randomize(null, null);
    }

    @Override
    public Collection<T> randomize(Range<Long> range) {
        return randomize(null, range);
    }

    public Collection<T> randomize(Range<Long> valueRange, Range<Long> sizeRange) {
        try {
            return implementRandomization(valueRange, sizeRange);
        } catch (Exception exception) {
            log.error(RANDOMIZE_BY_DEFAULT, exception.toString());
            return randomize();
        }
    }

    private List<T> implementRandomization(Range<Long> valueRange, Range<Long> sizeRange) {
        final long collectionSize = getCollectionSize(sizeRange);
        final Supplier<T> randomize = getSupplier(valueRange);

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

    private Supplier<T> getSupplier(Range<Long> range) {
        return Objects.isNull(range)
                ? primitiveRandomizer::randomize
                : () -> primitiveRandomizer.randomize(range);
    }
}
