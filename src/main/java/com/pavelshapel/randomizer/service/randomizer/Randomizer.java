package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.entity.valuespecification.AbstractValueSpecification;

public interface Randomizer<T> {
    String RANDOMIZE_BY_DEFAULT = "randomize by default, because an exception is thrown [{}]";

    T randomize(AbstractValueSpecification<T, ?> valueSpecification);
}
