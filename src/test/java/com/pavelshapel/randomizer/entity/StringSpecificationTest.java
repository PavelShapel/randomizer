package com.pavelshapel.randomizer.entity;

import com.pavelshapel.randomizer.provider.TwoLongProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThat;

class StringSpecificationTest {
    @Test
    void constructor_WithoutParams_ShouldReturnDefaultPojo() {
        final StringSpecification stringSpecification = new StringSpecification();

        assertThat(stringSpecification.getMin()).isEqualTo(AbstractSpecification.DEFAULT_POSITIVE);
        assertThat(stringSpecification.getMax()).isEqualTo(AbstractSpecification.DEFAULT_MAX);
        assertThat(stringSpecification.getGenericParameterClass()).isEqualTo(String.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void constructor_WithAllParams_ShouldReturnPojo(long min, long max) {
        final StringSpecification stringSpecification = new StringSpecification(min, max);

        assertThat(stringSpecification.getMin()).isEqualTo(Math.min(min, max));
        assertThat(stringSpecification.getMax()).isEqualTo(Math.max(min, max));
        assertThat(stringSpecification.getGenericParameterClass()).isEqualTo(String.class);
    }
}