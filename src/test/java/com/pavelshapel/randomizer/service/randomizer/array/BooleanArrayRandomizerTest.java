package com.pavelshapel.randomizer.service.randomizer.array;

import com.pavelshapel.randomizer.provider.FourParametersLongProvider;
import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
import com.pavelshapel.randomizer.service.randomizer.primitive.BooleanPrimitiveRandomizer;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {
        BooleanArrayRandomizer.class,
        BooleanPrimitiveRandomizer.class
}
)
class BooleanArrayRandomizerTest {
    @Autowired
    private BooleanArrayRandomizer arrayRandomizer;

    @Test
    void randomize_WithoutParams_ShouldReturnArray() {
        final Boolean[] randomArray = arrayRandomizer.randomize();

        final long arrayLength = Arrays.stream(randomArray)
                .peek(value -> assertThat(value).isInstanceOf(Boolean.class))
                .peek(value -> assertThat(value).isIn(false, true))
                .count();
        assertThat(arrayLength).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_RangeAsParam_ShouldReturnArray(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Boolean[] randomArray = arrayRandomizer.randomize(range);

        final long arrayLength = Arrays.stream(randomArray)
                .peek(value -> assertThat(value).isInstanceOf(Boolean.class))
                .peek(value -> assertThat(value).isIn(false, true))
                .count();
        assertThat(arrayLength).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }

    @Test
    void randomize_NullAsParam_ShouldReturnArray() {
        final Boolean[] randomArray = arrayRandomizer.randomize(null);

        final long arrayLength = Arrays.stream(randomArray)
                .peek(value -> assertThat(value).isInstanceOf(Boolean.class))
                .peek(value -> assertThat(value).isIn(false, true))
                .count();
        assertThat(arrayLength).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(FourParametersLongProvider.class)
    void randomize_ValueSizeRangesAsParams_ShouldReturnArray(long minValue, long maxValue, long minSize, long maxSize) {
        final Range<Long> rangeValue = Range.between(minValue, maxValue);
        final Range<Long> rangeSize = Range.between(minSize, maxSize);

        final Boolean[] randomArray = arrayRandomizer.randomize(rangeValue, rangeSize);

        final long arrayLength = Arrays.stream(randomArray)
                .peek(value -> assertThat(value).isInstanceOf(Boolean.class))
                .peek(value -> assertThat(value).isIn(false, true))
                .count();
        assertThat(arrayLength).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }
}