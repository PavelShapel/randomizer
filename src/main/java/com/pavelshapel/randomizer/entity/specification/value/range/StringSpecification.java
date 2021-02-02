package com.pavelshapel.randomizer.entity.specification.value.range;


import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class StringSpecification extends AbstractRangeSpecification<String> {
    public StringSpecification() {
        super(String.class);
    }

    @Override
    protected Range<Long> getDefaultInitialObject() {
        return DEFAULT_POSITIVE_BYTE_RANGE.getValue();
    }
}
