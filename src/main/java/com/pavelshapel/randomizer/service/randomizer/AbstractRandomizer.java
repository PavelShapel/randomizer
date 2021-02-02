package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.entity.specification.collection.CollectionSpecification;
import com.pavelshapel.randomizer.entity.specification.value.AbstractSpecification;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractRandomizer<T, O> {
    @Autowired
    private ObjectFactory<AbstractSpecification<T, O>> valueSpecification;
    @Autowired
    private ObjectFactory<CollectionSpecification> rangeSpecification;

    public T randomizeValue() {
        return getRandomValue(getValueSpecification());
    }

    public abstract T randomizeBoundedValue(long minValue, long maxValue);

    public Collection<T> randomizeCollection() {
        return Stream.generate(this::randomizeValue)
                .limit(getCollectionSize(getRangeSpecification()))
                .collect(Collectors.toList());
    }

//    public abstract T randomizeBoundedValueBoundedCollection(long minValue, long maxValue, long minArray, long maxArray);

    protected AbstractSpecification<T, O> getValueSpecification() {
        return valueSpecification.getObject();
    }

    protected CollectionSpecification getRangeSpecification() {
        return rangeSpecification.getObject();
    }

    protected abstract T getRandomValue(AbstractSpecification<T, O> valueSpecification);

    private long getCollectionSize(CollectionSpecification collectionSpecification) {
        return ThreadLocalRandom.current().nextLong(
                collectionSpecification.getInitialObject().getMinimum(),
                collectionSpecification.getInitialObject().getMaximum());
    }

}
