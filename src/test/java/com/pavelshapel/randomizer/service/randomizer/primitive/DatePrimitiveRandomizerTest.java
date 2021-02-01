//package com.pavelshapel.randomizer.service.randomizer.primitive;
//
//import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
//import org.apache.commons.lang3.Range;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.util.Date;
//
//import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_LONG_RANGE;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ContextConfiguration(classes = DatePrimitiveRandomizer.class)
//class DatePrimitiveRandomizerTest {
//    @Autowired
//    private DatePrimitiveRandomizer primitiveRandomizer;
//
//    @Test
//    void randomize_WithoutParams_ShouldReturnDate() {
//        final Date randomDate = primitiveRandomizer.randomize();
//
//        assertThat(randomDate.getTime()).isBetween(
//                DEFAULT_LONG_RANGE.getValue().getMinimum(),
//                DEFAULT_LONG_RANGE.getValue().getMaximum()
//        );
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(TwoParametersLongProvider.class)
//    void randomize_RangeAsParam_ShouldReturnDate(long min, long max) {
//        final Range<Long> range = Range.between(min, max);
//
//        final Date randomDate = primitiveRandomizer.randomize(range);
//
//        assertThat(randomDate.getTime()).isBetween(
//                DEFAULT_LONG_RANGE.getValue().getMinimum(),
//                DEFAULT_LONG_RANGE.getValue().getMaximum()
//        );
//        assertThat(randomDate).isInstanceOf(Date.class);
//    }
//
//    @Test
//    void randomize_NullAsParam_ShouldReturnDate() {
//        final Date randomDate = primitiveRandomizer.randomize(null);
//
//        assertThat(randomDate.getTime()).isBetween(
//                DEFAULT_LONG_RANGE.getValue().getMinimum(),
//                DEFAULT_LONG_RANGE.getValue().getMaximum()
//        );
//    }
//}