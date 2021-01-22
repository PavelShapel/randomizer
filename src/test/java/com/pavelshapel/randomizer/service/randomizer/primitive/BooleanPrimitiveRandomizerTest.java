package com.pavelshapel.randomizer.service.randomizer.primitive;

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
@ContextConfiguration(classes = BooleanPrimitiveRandomizer.class)
class BooleanPrimitiveRandomizerTest {
    @Autowired
    BooleanPrimitiveRandomizer booleanPrimitiveRandomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnBoolean() {
        final Boolean randomBoolean = booleanPrimitiveRandomizer.randomize();

        assertThat(randomBoolean).isIn(false, true);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnBoolean(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Boolean randomBoolean = booleanPrimitiveRandomizer.randomize(range);

        assertThat(randomBoolean).isIn(false, true);
    }

    @Test
    void randomizeCollection_ShouldReturnCollection() {
        final Collection<Boolean> randomCollection = booleanPrimitiveRandomizer.randomizeCollection();

        assertThat(randomCollection.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}