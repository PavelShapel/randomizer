package com.pavelshapel.randomizer.service;

import com.pavelshapel.randomizer.provider.TwoLongProvider;
import com.pavelshapel.randomizer.service.randomizer.BooleanRandomizer;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = BooleanRandomizer.class)
class BooleanRandomizerTest {
    @Autowired
    BooleanRandomizer randomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnBoolean() {
        final Boolean randomBoolean = randomizer.randomize();

        assertThat(randomBoolean).isIn(false, true);
        assertThat(randomizer.getGenericParameterClass()).isEqualTo(Boolean.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnBoolean(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Boolean randomBoolean = randomizer.randomize(range);

        assertThat(randomBoolean).isIn(false, true);
    }
}