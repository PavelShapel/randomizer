//package com.pavelshapel.randomizer.service.randomizer.primitive;
//
//import com.pavelshapel.randomizer.entity.valuespecification.AbstractValueSpecification;
//import com.pavelshapel.randomizer.service.randomizer.Randomizer;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.ThreadLocalRandom;
//
//@Service
//public final class LongPrimitiveRandomizer implements Randomizer<Long> {
//    @Override
//    public Long randomize(AbstractValueSpecification<Long> valueSpecification) {
//        return ThreadLocalRandom.current().nextLong(
//                valueSpecification.getRange().getMinimum(),
//                valueSpecification.getRange().getMaximum()
//        );
//    }
//}
