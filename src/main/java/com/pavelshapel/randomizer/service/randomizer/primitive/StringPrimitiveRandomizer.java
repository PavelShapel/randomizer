//package com.pavelshapel.randomizer.service.randomizer.primitive;
//
//import com.pavelshapel.randomizer.entity.specification.value.AbstractValueSpecification;
//import com.pavelshapel.randomizer.service.randomizer.Randomizer;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.stereotype.Service;
//
//@Service
//public final class StringPrimitiveRandomizer implements Randomizer<String> {
//    @Override
//    public String randomize(AbstractValueSpecification<String> valueSpecification) {
//        return RandomStringUtils.randomAlphanumeric(
//                valueSpecification.getRange().getMinimum().intValue(),
//                valueSpecification.getRange().getMaximum().intValue()
//        );
//    }
//}
