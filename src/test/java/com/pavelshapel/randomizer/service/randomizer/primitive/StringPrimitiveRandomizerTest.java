package com.pavelshapel.randomizer.service.randomizer.primitive;

import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
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
@ContextConfiguration(classes = StringPrimitiveRandomizer.class)
class StringPrimitiveRandomizerTest {
    private final StringPrimitiveRandomizer stringPrimitiveRandomizer;

    @Autowired
    StringPrimitiveRandomizerTest(StringPrimitiveRandomizer stringPrimitiveRandomizer) {
        this.stringPrimitiveRandomizer = stringPrimitiveRandomizer;
    }

    @Test
    void randomize_WithDefaultRange_ShouldReturnString() {
        final String randomString = stringPrimitiveRandomizer.randomize();

        assertThat(randomString.length()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedSpecification_ShouldReturnString(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        String randomString = stringPrimitiveRandomizer.randomize(range);

        assertThat(randomString.length()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }

    @Test
    void randomize_NullAsParam_ShouldReturnString() {
        final String randomString = stringPrimitiveRandomizer.randomize(null);

        assertThat(randomString.length()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}