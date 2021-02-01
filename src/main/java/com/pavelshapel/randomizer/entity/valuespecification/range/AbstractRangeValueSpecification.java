package com.pavelshapel.randomizer.entity.valuespecification.range;

import com.pavelshapel.randomizer.entity.valuespecification.AbstractValueSpecification;
import lombok.NonNull;
import org.apache.commons.lang3.Range;

public abstract class AbstractRangeValueSpecification<T> extends AbstractValueSpecification<T, Range<Long>> {
    protected AbstractRangeValueSpecification(@NonNull Class<T> genericClass) {
        super(genericClass);
    }

    protected AbstractRangeValueSpecification(@NonNull Class<T> genericClass, Range<Long> range) {
        super(genericClass, range);
    }

    @Override
    protected Range<Long> getVerifiedObject(Range<Long> range) {
        try {
            return getDefaultInitialObject().intersectionWith(range);
        } catch (Exception exception) {
            return getDefaultInitialObject();
        }
    }
}
