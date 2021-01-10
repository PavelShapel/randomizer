package com.pavelshapel.randomizer.service;

import com.pavelshapel.randomizer.provider.TwoLongProvider;
import com.pavelshapel.randomizer.service.randomizer.DoubleRandomizer;
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
@ContextConfiguration(classes = DoubleRandomizer.class)
class DoubleRandomizerTest {
    @Autowired
    DoubleRandomizer doubleRandomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnDouble() {
        final Double randomDouble = doubleRandomizer.randomize();

        assertThat(randomDouble).isBetween(
                DEFAULT_LONG_RANGE.getValue().getMinimum().doubleValue(),
                DEFAULT_LONG_RANGE.getValue().getMaximum().doubleValue()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnDouble(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Double randomDouble = doubleRandomizer.randomize(range);

        assertThat(randomDouble).isBetween(
                range.getMinimum().doubleValue(),
                range.getMaximum().doubleValue()
        );
    }
}