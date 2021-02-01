//package com.pavelshapel.randomizer.service.randomizer.primitive;
//
//import com.pavelshapel.randomizer.entity.valuespecification.range.LongValueSpecification;
//import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
//import org.apache.commons.lang3.Range;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ContextConfiguration(classes = LongPrimitiveRandomizer.class)
//class LongPrimitiveRandomizerTest {
//    @Autowired
//    private LongPrimitiveRandomizer primitiveRandomizer;
//
//    @Test
//    void randomize_WithoutParams_ShouldReturnLong() {
//        final LongValueSpecification valueSpecification = new LongValueSpecification();
//        final Long randomValue = primitiveRandomizer.randomize(valueSpecification);
//
//        assertThat(randomValue).isBetween(
//                valueSpecification.getRange().getMinimum(),
//                valueSpecification.getRange().getMaximum()
//        );
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(TwoParametersLongProvider.class)
//    void randomize_RangeAsParam_ShouldReturnLong(long min, long max) {
//        final Range<Long> range = Range.between(min, max);
//        final LongValueSpecification valueSpecification = new LongValueSpecification(range);
//
//        final Long randomValue = primitiveRandomizer.randomize(valueSpecification);
//
//        assertThat(randomValue).isBetween(
//                valueSpecification.getRange().getMinimum(),
//                valueSpecification.getRange().getMaximum()
//        );
//    }
//
//    @Test
//    void randomize_NullAsParam_ShouldReturnLong() {
//        final LongValueSpecification valueSpecification = new LongValueSpecification();
//        final Long randomValue = primitiveRandomizer.randomize(null);
//
//        assertThat(randomValue).isBetween(
//                valueSpecification.getRange().getMinimum(),
//                valueSpecification.getRange().getMaximum()
//        );
//    }
//}