package com.pavelshapel.randomizer.service.randomizer.primitive;

import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_LONG_RANGE;

@Service
public final class LongPrimitiveRandomizer extends PrimitiveRandomizer<Long> {
    @Override
    public Long randomize() {
        return randomize(DEFAULT_LONG_RANGE.getValue());
    }

    @Override
    protected Long randomizeRange(Range<Long> range) {
        return ThreadLocalRandom.current().nextLong(
                range.getMinimum(),
                range.getMaximum()
        );
    }
}
