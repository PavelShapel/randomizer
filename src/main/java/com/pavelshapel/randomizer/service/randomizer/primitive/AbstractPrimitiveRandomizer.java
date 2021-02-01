//package com.pavelshapel.randomizer.service.randomizer.primitive;
//
//import com.pavelshapel.randomizer.entity.valuespecification.AbstractValueSpecification;
//import com.pavelshapel.randomizer.service.randomizer.Randomizer;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@Log4j2
//public abstract class AbstractPrimitiveRandomizer<T> implements Randomizer<T> {
//    @Override
//    public T randomize() {
//        return getRandomValue();
//    }
//
//    @Override
//    public T randomize(long min, long max) {
//        try {
//            return getRandomValue(min, max);
//        } catch (Exception exception) {
//            log.error(RANDOMIZE_BY_DEFAULT, exception.toString());
//            return randomize();
//        }
//    }
//
//    protected abstract T getRandomValue(long min, long max);
//
//
//}