package com.pavelshapel.randomizer.service.randomizer.randomentity;

import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
import com.pavelshapel.randomizer.service.Utilities;
import com.pavelshapel.randomizer.service.randomizer.primitive.DoublePrimitiveRandomizer;
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
@ContextConfiguration(classes = {
        DoubleRandomEntityRandomizer.class,
        DoublePrimitiveRandomizer.class,
        Utilities.class})
class DoubleRandomEntityRandomizerTest {
    private final DoubleRandomEntityRandomizer doubleRandomEntityRandomizer;

    @Autowired
    DoubleRandomEntityRandomizerTest(DoubleRandomEntityRandomizer doubleRandomEntityRandomizer) {
        this.doubleRandomEntityRandomizer = doubleRandomEntityRandomizer;
    }

    @Test
    void randomize_WithDefaultRange_ShouldReturnRandomEntity() {
        final RandomEntity<Double> randomEntity = doubleRandomEntityRandomizer.randomize();

        assertThat(randomEntity.getValue()).isBetween(
                DEFAULT_LONG_RANGE.getValue().getMinimum().doubleValue(),
                DEFAULT_LONG_RANGE.getValue().getMaximum().doubleValue()
        );
        assertThat(randomEntity.getType()).isEqualTo(Double.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnRandomEntity(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final RandomEntity<Double> randomEntity = doubleRandomEntityRandomizer.randomize(range);

        assertThat(randomEntity.getValue()).isBetween(
                range.getMinimum().doubleValue(),
                range.getMaximum().doubleValue()
        );
        assertThat(randomEntity.getType()).isEqualTo(Double.class);
    }

    @Test
    void randomize_NullAsParam_ShouldReturnRandomEntity() {
        final RandomEntity<Double> randomEntity = doubleRandomEntityRandomizer.randomize(null);

        assertThat(randomEntity.getValue()).isBetween(
                DEFAULT_LONG_RANGE.getValue().getMinimum().doubleValue(),
                DEFAULT_LONG_RANGE.getValue().getMaximum().doubleValue()
        );
        assertThat(randomEntity.getType()).isEqualTo(Double.class);
    }
}