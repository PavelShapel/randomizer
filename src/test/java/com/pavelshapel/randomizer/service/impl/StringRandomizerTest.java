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
@ContextConfiguration(classes = StringRandomizer.class)
class StringRandomizerTest {
    @Autowired
    StringRandomizer stringRandomizer;

    @Test
    void randomize_WithDefaultSpecification_ShouldReturnLong() {
        final String randomString = stringRandomizer.randomize();

        assertThat(randomString.length()).isBetween(
                AbstractSpecification.DEFAULT_OFFSET,
                AbstractSpecification.DEFAULT_MAX
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void randomize_WithBoundedSpecification_ShouldReturnLong(long min, long max) {
        final LongSpecification longSpecification = new LongSpecification(min, max);

        final Long randomLong = stringRandomizer.randomize(longSpecification);

        assertThat(randomLong).isBetween(
                Math.min(min, max),
                Math.max(min, max)
        );
    }
}