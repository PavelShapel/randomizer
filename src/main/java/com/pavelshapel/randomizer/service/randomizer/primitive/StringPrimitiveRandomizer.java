package com.pavelshapel.randomizer.service.randomizer.primitive;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;

@Service
public final class StringPrimitiveRandomizer extends PrimitiveRandomizer<String> {
    @Override
    public String randomize() {
        return randomize(DEFAULT_POSITIVE_BYTE_RANGE.getValue());
    }

    @Override
    public String implementRandomization(Range<Long> range) {
        final Range<Long> intersectionWithPositiveByteRange =
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().intersectionWith(range);

        return RandomStringUtils.randomAlphanumeric(
                intersectionWithPositiveByteRange.getMinimum().intValue(),
                intersectionWithPositiveByteRange.getMaximum().intValue()
        );
    }
}
