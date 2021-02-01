//package com.pavelshapel.randomizer.service.randomizer.primitive;
//
//import com.pavelshapel.randomizer.entity.valuespecification.AbstractValueSpecification;
//import com.pavelshapel.randomizer.service.randomizer.Randomizer;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.ThreadLocalRandom;
//
//@Service
//public final class BooleanPrimitiveRandomizer implements Randomizer<Boolean> {
//    @Override
//    public Boolean randomize(AbstractValueSpecification<Boolean> valueSpecification) {
//        return ThreadLocalRandom.current().nextBoolean();
//    }
//}
