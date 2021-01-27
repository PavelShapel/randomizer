package com.pavelshapel.randomizer.service.randomizer.primitive;

import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public final class BooleanPrimitiveRandomizer extends PrimitiveRandomizer<Boolean> {
    public BooleanPrimitiveRandomizer() {
        super(Boolean.class);
    }

    @Override
    public Boolean randomize() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    @Override
    protected Boolean implementRandomization(Range<Long> range) {
        return randomize();
    }
}
