package com.pavelshapel.randomizer.entity.valuespecification.range;


import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_LONG_RANGE;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LongValueSpecification extends AbstractRangeValueSpecification<Long> {
    public LongValueSpecification() {
        super(Long.class);
    }

    public LongValueSpecification(Range<Long> range) {
        super(Long.class, range);
    }

    @Override
    protected Range<Long> getDefaultInitialObject() {
        return DEFAULT_LONG_RANGE.getValue();
    }
}
