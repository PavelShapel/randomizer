package com.pavelshapel.randomizer.service.randomizer.primitive;

import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_LONG_RANGE;
import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;

@Service
public final class DoublePrimitiveRandomizer extends PrimitiveRandomizer<Double> {
    public DoublePrimitiveRandomizer() {
        super(Double.class);
    }

    @Override
    public Double randomize() {
        return randomize(DEFAULT_LONG_RANGE.getValue());
    }

    @Override
    protected Double implementRandomization(Range<Long> range) {
        final double randomDouble = ThreadLocalRandom.current().nextDouble(
                range.getMinimum().doubleValue(),
                range.getMaximum().doubleValue()
        );
        final BigDecimal scaledBigDecimal = BigDecimal.valueOf(randomDouble).setScale(
                getRandomizedScale(),
                RoundingMode.HALF_UP
        );

        return scaledBigDecimal.doubleValue();
    }

    private int getRandomizedScale() {
        return ThreadLocalRandom.current().nextInt(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}
