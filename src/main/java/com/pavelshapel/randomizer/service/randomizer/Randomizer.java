package com.pavelshapel.randomizer.service.randomizer;

import org.apache.commons.lang3.Range;

import java.util.Collection;

public interface Randomizer<T> {
    T randomize();

    T randomize(Range<Long> range);

    Collection<T> randomizeCollection();
}
