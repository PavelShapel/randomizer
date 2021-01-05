package com.pavelshapel.randomizer.service.impl;

import com.pavelshapel.randomizer.entity.AbstractSpecification;
import com.pavelshapel.randomizer.entity.DateSpecification;
import com.pavelshapel.randomizer.service.BoundedRandomizer;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DateRandomizer implements BoundedRandomizer<Date> {
    @Override
    public Date randomize() {
        return randomize(new DateSpecification());
    }

    @Override
    public Date randomize(@NonNull AbstractSpecification<Date> specification) {
        final long randomLong = ThreadLocalRandom.current().nextLong(
                specification.getMin(),
                specification.getMax()
        );
        return new Date(randomLong);
    }
}
