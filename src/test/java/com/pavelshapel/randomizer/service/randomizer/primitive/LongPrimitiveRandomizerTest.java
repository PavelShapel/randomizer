package com.pavelshapel.randomizer.service.randomizer.primitive;

import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_LONG_RANGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = LongPrimitiveRandomizer.class)
class LongPrimitiveRandomizerTest {
    @Autowired
    private LongPrimitiveRandomizer primitiveRandomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnLong() {
        final Long randomLong = primitiveRandomizer.randomize();

        assertThat(randomLong).isBetween(
                DEFAULT_LONG_RANGE.getValue().getMinimum(),
                DEFAULT_LONG_RANGE.getValue().getMaximum()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnLong(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Long randomLong = primitiveRandomizer.randomize(range);

        assertThat(randomLong).isBetween(
                range.getMinimum(),
                range.getMaximum()
        );
    }

    @Test
    void randomize_NullAsParam_ShouldReturnLong() {
        final Long randomLong = primitiveRandomizer.randomize(null);

        assertThat(randomLong).isBetween(
                DEFAULT_LONG_RANGE.getValue().getMinimum(),
                DEFAULT_LONG_RANGE.getValue().getMaximum()
        );
    }
}