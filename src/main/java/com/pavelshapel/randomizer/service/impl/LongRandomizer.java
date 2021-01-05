package com.pavelshapel.randomizer.service.impl;

import com.pavelshapel.randomizer.entity.AbstractSpecification;
import com.pavelshapel.randomizer.entity.LongSpecification;
import com.pavelshapel.randomizer.service.BoundedRandomizer;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class LongRandomizer implements BoundedRandomizer<Long> {
    @Override
    public Long randomize() {
        return randomize(new LongSpecification());
    }

    @Override
    public Long randomize(@NonNull AbstractSpecification<Long> specification) {
        return ThreadLocalRandom.current().nextLong(
                specification.getMin(),
                specification.getMax()
        );
    }
}
