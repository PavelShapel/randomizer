package com.pavelshapel.randomizer.service.randomizer.primitive;

import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collection;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_LONG_RANGE;
import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = DoublePrimitiveRandomizer.class)
class DoublePrimitiveRandomizerTest {
    @Autowired
    DoublePrimitiveRandomizer doublePrimitiveRandomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnDouble() {
        final Double randomDouble = doublePrimitiveRandomizer.randomize();

        assertThat(randomDouble).isBetween(
                DEFAULT_LONG_RANGE.getValue().getMinimum().doubleValue(),
                DEFAULT_LONG_RANGE.getValue().getMaximum().doubleValue()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnDouble(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Double randomDouble = doublePrimitiveRandomizer.randomize(range);

        assertThat(randomDouble).isBetween(
                range.getMinimum().doubleValue(),
                range.getMaximum().doubleValue()
        );
    }

    @Test
    void randomizeCollection_ShouldReturnCollection() {
        final Collection<Double> randomCollection = doublePrimitiveRandomizer.randomizeCollection();

        assertThat(randomCollection.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}