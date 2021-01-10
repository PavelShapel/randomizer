package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.service.BoundedRandomizer;
import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_LONG_RANGE;

@Service
public final class DateRandomizer extends BoundedRandomizer<Date> {
    @Override
    public Date randomize() {
        return randomize(DEFAULT_LONG_RANGE.getValue());
    }

    @Override
    protected Date randomizeRange(Range<Long> range) {
        final long randomLong = ThreadLocalRandom.current().nextLong(
                range.getMinimum(),
                range.getMaximum()
        );

        return new Date(randomLong);
    }
}
