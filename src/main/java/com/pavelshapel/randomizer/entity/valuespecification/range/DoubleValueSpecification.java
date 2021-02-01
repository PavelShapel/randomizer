package com.pavelshapel.randomizer.entity.valuespecification.range;


import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_LONG_RANGE;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DoubleValueSpecification extends AbstractRangeValueSpecification<Double> {
    public DoubleValueSpecification() {
        super(Double.class);
    }

    public DoubleValueSpecification(Range<Long> range) {
        super(Double.class, range);
    }

    @Override
    protected Range<Long> getDefaultInitialObject() {
        return DEFAULT_LONG_RANGE.getValue();
    }
}
