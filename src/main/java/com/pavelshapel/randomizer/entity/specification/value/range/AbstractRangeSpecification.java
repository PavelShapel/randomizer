package com.pavelshapel.randomizer.entity.specification.value.range;

import com.pavelshapel.randomizer.entity.specification.value.AbstractSpecification;
import lombok.NonNull;
import org.apache.commons.lang3.Range;

public abstract class AbstractRangeSpecification<T> extends AbstractSpecification<T, Range<Long>> {
    protected AbstractRangeSpecification(@NonNull Class<T> valueClass) {
        super(valueClass);
    }

    protected Range<Long> getVerifiedInitialObject(Range<Long> range) {
        try {
            return getDefaultInitialObject().intersectionWith(range);
        } catch (Exception exception) {
            return getDefaultInitialObject();
        }
    }
}
