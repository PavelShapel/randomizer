package com.pavelshapel.randomizer.service.impl;

import com.pavelshapel.randomizer.entity.AbstractSpecification;
import com.pavelshapel.randomizer.entity.LongSpecification;
import com.pavelshapel.randomizer.provider.TwoLongProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = LongRandomizer.class)
class LongRandomizerTest {
    @Autowired
    LongRandomizer longRandomizer;

    @Test
    void randomize_WithDefaultSpecification_ShouldReturnLong() {
        final Long randomLong = longRandomizer.randomize();

        assertThat(randomLong).isBetween(
                AbstractSpecification.DEFAULT_MIN_LONG,
                AbstractSpecification.DEFAULT_MAX_LONG
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void randomize_WithBoundedSpecification_ShouldReturnLong(long min, long max) {
        final LongSpecification longSpecification = new LongSpecification(min, max);
        final long validMin = longSpecification.getMin();
        final long validMax = longSpecification.getMax();

        final Long randomLong = longRandomizer.randomize(longSpecification);

        assertThat(randomLong).isBetween(
                validMin,
                validMax
        );
    }
}