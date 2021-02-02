package com.pavelshapel.randomizer.entity.specification.value.range;


import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_YEAR_RANGE;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class DateSpecification extends AbstractRangeSpecification<Date> {
    public DateSpecification() {
        super(Date.class);
    }

    @Override
    protected Range<Long> getDefaultInitialObject() {
        return DEFAULT_YEAR_RANGE.getValue();
    }
}
