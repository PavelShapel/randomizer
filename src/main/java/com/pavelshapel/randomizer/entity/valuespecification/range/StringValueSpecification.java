package com.pavelshapel.randomizer.entity.valuespecification.range;


import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StringValueSpecification extends AbstractRangeValueSpecification<String> {
    public StringValueSpecification() {
        super(String.class);
    }

    public StringValueSpecification(Range<Long> range) {
        super(String.class, range);
    }

    @Override
    protected Range<Long> getDefaultInitialObject() {
        return DEFAULT_POSITIVE_BYTE_RANGE.getValue();
    }
}
