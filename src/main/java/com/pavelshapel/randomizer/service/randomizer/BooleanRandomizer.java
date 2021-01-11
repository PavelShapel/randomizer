package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.service.Randomizer;
import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public final class BooleanRandomizer extends Randomizer<Boolean> {
    @Override
    public Boolean randomize() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    @Override
    protected Boolean randomizeRange(Range<Long> range) {
        return randomize();
    }
}
