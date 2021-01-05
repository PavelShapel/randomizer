package com.pavelshapel.randomizer.entity;

import com.pavelshapel.randomizer.provider.ThreeLongProvider;
import com.pavelshapel.randomizer.provider.TwoLongProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThat;

class DoubleSpecificationTest {
    @Test
    void constructor_WithoutParams_ShouldReturnDefaultPojo() {
        final DoubleSpecification doubleSpecification = new DoubleSpecification();

        assertThat(doubleSpecification.getMin()).isEqualTo(AbstractSpecification.DEFAULT_MIN);
        assertThat(doubleSpecification.getMax()).isEqualTo(AbstractSpecification.DEFAULT_MAX);
        assertThat(doubleSpecification.getPrecision()).isEqualTo(AbstractSpecification.DEFAULT_POSITIVE);
        assertThat(doubleSpecification.getGenericParameterClass()).isEqualTo(Double.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void constructor_WithTwoParams_ShouldReturnPojo(long min, long max) {
        final DoubleSpecification doubleSpecification = new DoubleSpecification(min, max);

        assertThat(doubleSpecification.getMin()).isEqualTo(Math.min(min, max));
        assertThat(doubleSpecification.getMax()).isEqualTo(Math.max(min, max));
        assertThat(doubleSpecification.getPrecision()).isEqualTo(AbstractSpecification.DEFAULT_POSITIVE);
        assertThat(doubleSpecification.getGenericParameterClass()).isEqualTo(Double.class);
    }

    @ParameterizedTest
    @ArgumentsSource(ThreeLongProvider.class)
    void constructor_WithAllParams_ShouldReturnPojo(long min, long max, long precision) {
        final DoubleSpecification doubleSpecification = new DoubleSpecification(min, max, precision);

        assertThat(doubleSpecification.getMin()).isEqualTo(Math.min(min, max));
        assertThat(doubleSpecification.getMax()).isEqualTo(Math.max(min, max));
        assertThat(doubleSpecification.getPrecision()).isEqualTo(precision);
        assertThat(doubleSpecification.getGenericParameterClass()).isEqualTo(Double.class);
    }
}