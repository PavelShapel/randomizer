package com.pavelshapel.randomizer.entity.valuespecification.range;


import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_YEAR_RANGE;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DateValueSpecification extends AbstractRangeValueSpecification<Date> {
    public DateValueSpecification() {
        super(Date.class);
    }

    public DateValueSpecification(Range<Long> range) {
        super(Date.class, range);
    }

    @Override
    protected Range<Long> getDefaultInitialObject() {
        return DEFAULT_YEAR_RANGE.getValue();
    }
}
