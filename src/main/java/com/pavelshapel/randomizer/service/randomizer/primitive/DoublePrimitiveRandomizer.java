//package com.pavelshapel.randomizer.service.randomizer.primitive;
//
//import com.pavelshapel.randomizer.entity.valuespecification.AbstractValueSpecification;
//import com.pavelshapel.randomizer.service.randomizer.Randomizer;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.concurrent.ThreadLocalRandom;
//
//import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
//
//@Service
//public final class DoublePrimitiveRandomizer implements Randomizer<Double> {
//    @Override
//    public Double randomize(AbstractValueSpecification<Double> valueSpecification) {
//        final double randomDouble = ThreadLocalRandom.current().nextDouble(
//                valueSpecification.getRange().getMinimum().doubleValue(),
//                valueSpecification.getRange().getMaximum().doubleValue()
//        );
//        final BigDecimal scaledBigDecimal = BigDecimal.valueOf(randomDouble).setScale(
//                getRandomizedScale(),
//                RoundingMode.HALF_UP
//        );
//        return scaledBigDecimal.doubleValue();
//    }
//
//    private int getRandomizedScale() {
//        return ThreadLocalRandom.current().nextInt(
//                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
//                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
//        );
//    }
//}
