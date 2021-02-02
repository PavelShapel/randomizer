package com.pavelshapel.randomizer.entity.specification.value.range;


import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_LONG_RANGE;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class BooleanSpecification extends AbstractRangeSpecification<Boolean> {
    public BooleanSpecification() {
        super(Boolean.class);
    }

    @Override
    protected Range<Long> getDefaultInitialObject() {
        return DEFAULT_LONG_RANGE.getValue();
    }
}
