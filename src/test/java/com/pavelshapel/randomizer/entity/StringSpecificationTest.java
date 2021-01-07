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

        assertThat(stringSpecification.getMin()).isEqualTo(AbstractSpecification.DEFAULT_MIN_BYTE);
        assertThat(stringSpecification.getMax()).isEqualTo(AbstractSpecification.DEFAULT_MAX_BYTE);
        assertThat(stringSpecification.getGenericParameterClass()).isEqualTo(String.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void constructor_WithAllParams_ShouldReturnPojo(long min, long max) {
        final int verifiedMinBound = StringSpecification.getVerifiedBound(min);
        final int verifiedMaxBound = StringSpecification.getVerifiedBound(max);
        final long validMin = Math.min(verifiedMinBound, verifiedMaxBound);
        final long validMax = Math.max(verifiedMinBound, verifiedMaxBound);

        final StringSpecification stringSpecification = new StringSpecification(min, max);

        assertThat(stringSpecification.getMin()).isEqualTo(validMin);
        assertThat(stringSpecification.getMax()).isEqualTo(validMax);
        assertThat(stringSpecification.getGenericParameterClass()).isEqualTo(String.class);
    }
}