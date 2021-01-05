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

        assertThat(dateSpecification.getMin()).isEqualTo(AbstractSpecification.DEFAULT_MIN);
        assertThat(dateSpecification.getMax()).isEqualTo(AbstractSpecification.DEFAULT_MAX);
        assertThat(dateSpecification.getGenericParameterClass()).isEqualTo(Date.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void constructor_WithAllParams_ShouldReturnPojo(long min, long max) {
        final DateSpecification dateSpecification = new DateSpecification(
                new Date(min),
                new Date(max)
        );

        assertThat(dateSpecification.getMin()).isEqualTo(Math.min(min, max));
        assertThat(dateSpecification.getMax()).isEqualTo(Math.max(min, max));
        assertThat(dateSpecification.getGenericParameterClass()).isEqualTo(Date.class);
    }
}