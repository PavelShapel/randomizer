package com.pavelshapel.randomizer.service.randomizer.randomentity;

import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.service.Utilities;
import com.pavelshapel.randomizer.service.randomizer.Randomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class RandomEntityRandomizer<T> implements Randomizer<RandomEntity<T>> {
    private final PrimitiveRandomizer<T> primitiveRandomizer;
    @Autowired
    private Utilities utilities;

    protected RandomEntityRandomizer(PrimitiveRandomizer<T> primitiveRandomizer) {
        this.primitiveRandomizer = primitiveRandomizer;
    }

    @Override
    public RandomEntity<T> randomize() {
        return getRandomEntity(primitiveRandomizer.randomize());
    }

    @Override
    public RandomEntity<T> randomize(Range<Long> range) {
        return getRandomEntity(primitiveRandomizer.randomize(range));
    }

    @Override
    public Collection<RandomEntity<T>> randomizeCollection() {
        return primitiveRandomizer.randomizeCollection().stream()
                .map(this::getRandomEntity)
                .collect(Collectors.toList());
    }

    private RandomEntity<T> getRandomEntity(T value) {
        return new RandomEntity<>(value, getGenericParameterClass());
    }

    private Class<T> getGenericParameterClass() {
        return (Class<T>) utilities.getSuperClassGenericType(primitiveRandomizer, 0);
    }
}
