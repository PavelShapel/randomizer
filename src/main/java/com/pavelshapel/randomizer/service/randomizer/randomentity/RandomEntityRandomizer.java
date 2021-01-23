package com.pavelshapel.randomizer.service.randomizer.randomentity;

import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.service.Utilities;
import com.pavelshapel.randomizer.service.randomizer.Randomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
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
        try {
            return getRandomEntity(primitiveRandomizer.randomize(range));
        } catch (Exception exception) {
            log.error(RANDOMIZE_BY_DEFAULT, exception.toString());
            return randomize();
        }
    }

    private RandomEntity<T> getRandomEntity(T value) {
        return new RandomEntity<>(value, getGenericParameterClass());
    }

    private Class<T> getGenericParameterClass() {
        return (Class<T>) utilities.getSuperClassGenericType(primitiveRandomizer, 0);
    }
}
