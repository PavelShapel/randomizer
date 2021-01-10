package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.service.Randomizer;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public final class BooleanRandomizer extends Randomizer<Boolean> {
    @Override
    public Boolean randomize() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
