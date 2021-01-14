package com.pavelshapel.randomizer.service.randomizer;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;

@Service
public final class StringRandomizer extends Randomizer<String> {
    @Override
    public String randomize() {
        return randomize(DEFAULT_POSITIVE_BYTE_RANGE.getValue());
    }

    @Override
    protected String randomizeRange(Range<Long> range) {
        final Range<Long> intersectionWithPositiveByteRange =
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().intersectionWith(range);

        return RandomStringUtils.randomAlphanumeric(
                intersectionWithPositiveByteRange.getMinimum().intValue(),
                intersectionWithPositiveByteRange.getMaximum().intValue()
        );
    }
}
