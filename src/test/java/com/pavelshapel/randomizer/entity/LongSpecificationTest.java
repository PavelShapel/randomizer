package com.pavelshapel.randomizer.entity;

import com.pavelshapel.randomizer.provider.TwoLongProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThat;

class LongSpecificationTest {
    @Test
    void constructor_WithoutParams_ShouldReturnDefaultPojo() {
        final LongSpecification longSpecification = new LongSpecification();

        assertThat(longSpecification.getMin()).isEqualTo(AbstractSpecification.DEFAULT_MIN);
        assertThat(longSpecification.getMax()).isEqualTo(AbstractSpecification.DEFAULT_MAX);
        assertThat(longSpecification.getGenericParameterClass()).isEqualTo(Long.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void constructor_WithAllParams_ShouldReturnPojo(long min, long max) {
        final LongSpecification longSpecification = new LongSpecification(min, max);

        assertThat(longSpecification.getMin()).isEqualTo(Math.min(min, max));
        assertThat(longSpecification.getMax()).isEqualTo(Math.max(min, max));
        assertThat(longSpecification.getGenericParameterClass()).isEqualTo(Long.class);
    }
}