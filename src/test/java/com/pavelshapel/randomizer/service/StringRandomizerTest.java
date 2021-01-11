package com.pavelshapel.randomizer.service;

import com.pavelshapel.randomizer.provider.TwoLongProvider;
import com.pavelshapel.randomizer.service.randomizer.StringRandomizer;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = StringRandomizer.class)
class StringRandomizerTest {
    @Autowired
    StringRandomizer randomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnString() {
        final String randomString = randomizer.randomize();

        assertThat(randomString.length()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
        assertThat(randomizer.getGenericParameterClass()).isEqualTo(String.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void randomize_WithBoundedSpecification_ShouldReturnString(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        String randomString = randomizer.randomize(range);

        assertThat(randomString.length()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}