package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.service.BoundedRandomizer;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;

@Service
public final class StringRandomizer extends BoundedRandomizer<String> {
    @Override
    public String randomize() {
        return randomize(DEFAULT_POSITIVE_BYTE_RANGE.getValue());
    }

    @Override
    protected String randomizeRange(Range<Long> range) {
        return RandomStringUtils.randomAlphanumeric(
                Math.toIntExact(fitPositiveByteRange(range.getMinimum())),
                Math.toIntExact(fitPositiveByteRange(range.getMaximum()))
        );
    }

    private long fitPositiveByteRange(long value) {
        return DEFAULT_POSITIVE_BYTE_RANGE.getValue().fit(value);
    }
}
