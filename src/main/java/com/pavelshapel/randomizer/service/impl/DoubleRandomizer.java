package com.pavelshapel.randomizer.service.impl;

import com.pavelshapel.randomizer.entity.AbstractSpecification;
import com.pavelshapel.randomizer.entity.DoubleSpecification;
import com.pavelshapel.randomizer.service.BoundedRandomizer;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DoubleRandomizer implements BoundedRandomizer<Double> {
    @Override
    public Double randomize() {
        return randomize(new DoubleSpecification());
    }

    @Override
    public Double randomize(@NonNull AbstractSpecification<Double> specification) {
        final DoubleSpecification doubleSpecification = (DoubleSpecification) specification;
        final double randomDouble = ThreadLocalRandom.current().nextDouble(
                doubleSpecification.getMin(),
                doubleSpecification.getMax()
        );
        final BigDecimal scaledBigDecimal = BigDecimal.valueOf(randomDouble).setScale(
                Math.toIntExact(doubleSpecification.getPrecision()),
                RoundingMode.HALF_UP
        );
        return scaledBigDecimal.doubleValue();
    }
}
