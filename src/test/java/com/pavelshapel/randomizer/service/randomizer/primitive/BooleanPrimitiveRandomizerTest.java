package com.pavelshapel.randomizer.service.randomizer.primitive;

import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = BooleanPrimitiveRandomizer.class)
class BooleanPrimitiveRandomizerTest {
    @Autowired
    private BooleanPrimitiveRandomizer booleanPrimitiveRandomizer;

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
    void randomize_NullAsParam_ShouldReturnBoolean() {
        final Boolean randomBoolean = booleanPrimitiveRandomizer.randomize(null);

        assertThat(randomBoolean).isIn(false, true);
    }
}