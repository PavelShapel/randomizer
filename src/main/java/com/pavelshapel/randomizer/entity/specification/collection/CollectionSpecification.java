package com.pavelshapel.randomizer.entity.specification.collection;

import com.pavelshapel.randomizer.entity.specification.value.AbstractSpecification;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CollectionSpecification extends AbstractSpecification<Byte, Range<Long>> {
    protected CollectionSpecification() {
        super(Byte.class);
    }

    @Override
    protected Range<Long> getVerifiedInitialObject(Range<Long> range) {
        try {
            return getDefaultInitialObject().intersectionWith(range);
        } catch (Exception exception) {
            return getDefaultInitialObject();
        }
    }

    @Override
    protected Range<Long> getDefaultInitialObject() {
        return DEFAULT_POSITIVE_BYTE_RANGE.getValue();
    }
}
