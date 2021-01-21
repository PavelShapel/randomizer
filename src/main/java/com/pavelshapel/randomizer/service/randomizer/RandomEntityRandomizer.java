package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.entity.RandomEntity;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public final class RandomEntityRandomizer<T> implements Randomizer<RandomEntity<T>> {
    private final PrimitiveRandomizer<T> primitiveRandomizer;
    private final Class<T> genericParameterClass;

    @Autowired
    public RandomEntityRandomizer(PrimitiveRandomizer<T> primitiveRandomizer) {
        this.primitiveRandomizer = primitiveRandomizer;
        final ParameterizedType genericSuperclass = (ParameterizedType) this.primitiveRandomizer
                .getClass().getGenericSuperclass();
        this.genericParameterClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
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
        return new RandomEntity<>(value, genericParameterClass);
    }
}
