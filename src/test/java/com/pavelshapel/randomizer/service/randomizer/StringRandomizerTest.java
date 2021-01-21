package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collection;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = StringRandomizer.class)
class StringRandomizerTest {
    private final StringRandomizer stringRandomizer;

    @Autowired
    StringRandomizerTest(StringRandomizer stringRandomizer) {
        this.stringRandomizer = stringRandomizer;
    }

    @Test
    void randomize_WithDefaultRange_ShouldReturnString() {
        final String randomString = stringRandomizer.randomize();

        assertThat(randomString.length()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedSpecification_ShouldReturnString(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        String randomString = stringRandomizer.randomize(range);

        assertThat(randomString.length()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }

    @Test
    void randomizeCollection_ShouldReturnCollection() {
        final Collection<String> randomCollection = stringRandomizer.randomizeCollection();

        assertThat(randomCollection.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}