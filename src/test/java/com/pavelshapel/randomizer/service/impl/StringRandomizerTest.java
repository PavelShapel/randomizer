package com.pavelshapel.randomizer.service.impl;

import com.pavelshapel.randomizer.entity.AbstractSpecification;
import com.pavelshapel.randomizer.entity.StringSpecification;
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
    void randomize_WithDefaultSpecification_ShouldReturnString() {
        final String randomString = stringRandomizer.randomize();

        assertThat(randomString.length()).isBetween(
                Math.toIntExact(AbstractSpecification.DEFAULT_MIN_BYTE),
                Math.toIntExact(AbstractSpecification.DEFAULT_MAX_BYTE)
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void randomize_WithBoundedSpecification_ShouldReturnString(long min, long max) {
        StringSpecification stringSpecification = new StringSpecification(min, max);
        final long validMin = stringSpecification.getMin();
        final long validMax = stringSpecification.getMax();

        String randomString = stringRandomizer.randomize(stringSpecification);

        assertThat(randomString.length()).isBetween(
                Math.toIntExact(validMin),
                Math.toIntExact(validMax)
        );
    }
}