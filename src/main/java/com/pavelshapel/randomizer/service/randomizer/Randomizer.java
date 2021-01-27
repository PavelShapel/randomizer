package com.pavelshapel.randomizer.service.randomizer;

import org.apache.commons.lang3.Range;

public interface Randomizer<T> {
    String RANDOMIZE_BY_DEFAULT = "randomize by default, because an exception is thrown [{}]";

    Class<T> getGenericClass();

    T randomize();

    T randomize(Range<Long> range);
}
