package com.pavelshapel.randomizer.entity;

import com.pavelshapel.randomizer.provider.TwoLongProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class DateSpecificationTest {
    @Test
    void constructor_WithoutParams_ShouldReturnDefaultPojo() {
        final DateSpecification dateSpecification = new DateSpecification();

        assertThat(dateSpecification.getMin()).isEqualTo(AbstractSpecification.DEFAULT_MIN_LONG);
        assertThat(dateSpecification.getMax()).isEqualTo(AbstractSpecification.DEFAULT_MAX_LONG);
        assertThat(dateSpecification.getGenericParameterClass()).isEqualTo(Date.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void constructor_WithAllParams_ShouldReturnPojo(long min, long max) {
        final long validMin = Math.min(min, max);
        final long validMax = Math.max(min, max);

        final DateSpecification dateSpecification = new DateSpecification(
                new Date(min),
                new Date(max)
        );

        assertThat(dateSpecification.getMin()).isEqualTo(validMin);
        assertThat(dateSpecification.getMax()).isEqualTo(validMax);
        assertThat(dateSpecification.getGenericParameterClass()).isEqualTo(Date.class);
    }
}