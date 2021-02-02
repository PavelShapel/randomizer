package com.pavelshapel.randomizer.service.randomizer.primitive;

import com.pavelshapel.randomizer.entity.specification.value.AbstractSpecification;
import com.pavelshapel.randomizer.service.randomizer.AbstractRandomizer;
import org.apache.commons.lang3.Range;

public abstract class AbstractRangeRandomizer<T> extends AbstractRandomizer<T, Range<Long>> {
    @Override
    public T randomizeBoundedValue(long minValue, long maxValue) {
        final AbstractSpecification<T, Range<Long>> valueSpecification = getValueSpecification();
        valueSpecification.setInitialObject(Range.between(minValue, maxValue));
        return getRandomValue(valueSpecification);
    }
}