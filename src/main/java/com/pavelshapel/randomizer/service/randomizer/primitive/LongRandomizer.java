package com.pavelshapel.randomizer.service.randomizer.primitive;

import com.pavelshapel.randomizer.entity.specification.value.AbstractSpecification;
import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public final class LongRandomizer extends AbstractRangeRandomizer<Long> {
    @Override
    public Long randomizeBoundedValue(long minValue, long maxValue) {
        final AbstractSpecification<Long, Range<Long>> valueSpecification = getValueSpecification();
        valueSpecification.setInitialObject(Range.between(minValue, maxValue));
        return getRandomValue(valueSpecification);
    }

    @Override
    protected Long getRandomValue(AbstractSpecification<Long, Range<Long>> valueSpecification) {
        return ThreadLocalRandom.current().nextLong(
                valueSpecification.getInitialObject().getMinimum(),
                valueSpecification.getInitialObject().getMaximum()
        );
    }
}
