package com.pavelshapel.randomizer.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Range;

@Slf4j
public abstract class BoundedRandomizer<T> extends Randomizer<T> {
    public T randomize(@NonNull Range<Long> range) {
        try {
            return randomizeRange(range);
        } catch (Exception e) {
            log.error("randomize by default, because an exception is thrown [{}]", e.toString());
            return randomize();
        }
    }

    protected abstract T randomizeRange(Range<Long> range);
}
