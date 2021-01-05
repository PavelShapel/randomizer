package com.pavelshapel.randomizer.service.impl;

import com.pavelshapel.randomizer.service.Randomizer;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class BooleanRandomizer implements Randomizer<Boolean> {
    @Override
    public Boolean randomize() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
