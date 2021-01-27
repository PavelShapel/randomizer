package com.pavelshapel.randomizer.service.randomizer.array;

import com.pavelshapel.randomizer.provider.FourParametersLongProvider;
import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
import com.pavelshapel.randomizer.service.randomizer.primitive.DatePrimitiveRandomizer;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.Date;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_LONG_RANGE;
import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {
        DateArrayRandomizer.class,
        DatePrimitiveRandomizer.class
}
)
class DateArrayRandomizerTest {
    @Autowired
    private DateArrayRandomizer arrayRandomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnArray() {
        final Date[] randomArray = arrayRandomizer.randomize();

        final long arrayLength = Arrays.stream(randomArray)
                .peek(value -> assertThat(value).isInstanceOf(Date.class))
                .peek(value -> assertThat(value.getTime()).isBetween(
                        DEFAULT_LONG_RANGE.getValue().getMinimum(),
                        DEFAULT_LONG_RANGE.getValue().getMaximum()))
                .count();
        assertThat(arrayLength).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedSizeRange_ShouldReturnArray(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Date[] randomArray = arrayRandomizer.randomize(range);

        final long arrayLength = Arrays.stream(randomArray)
                .peek(value -> assertThat(value).isInstanceOf(Date.class))
                .peek(value -> assertThat(value.getTime()).isBetween(
                        DEFAULT_LONG_RANGE.getValue().getMinimum(),
                        DEFAULT_LONG_RANGE.getValue().getMaximum()))
                .count();
        assertThat(arrayLength).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }

    @Test
    void randomize_NullAsParam_ShouldReturnArray() {
        final Date[] randomArray = arrayRandomizer.randomize(null);

        final long arrayLength = Arrays.stream(randomArray)
                .peek(value -> assertThat(value).isInstanceOf(Date.class))
                .peek(value -> assertThat(value.getTime()).isBetween(
                        DEFAULT_LONG_RANGE.getValue().getMinimum(),
                        DEFAULT_LONG_RANGE.getValue().getMaximum()))
                .count();
        assertThat(arrayLength).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(FourParametersLongProvider.class)
    void randomize_WithBoundedValueSizeRange_ShouldReturnArray(long minValue, long maxValue, long minSize, long maxSize) {
        final Range<Long> rangeValue = Range.between(minValue, maxValue);
        final Range<Long> rangeSize = Range.between(minSize, maxSize);

        final Date[] randomArray = arrayRandomizer.randomize(rangeValue, rangeSize);

        final long arrayLength = Arrays.stream(randomArray)
                .peek(value -> assertThat(value).isInstanceOf(Date.class))
                .peek(value -> assertThat(value.getTime()).isBetween(
                        DEFAULT_LONG_RANGE.getValue().getMinimum(),
                        DEFAULT_LONG_RANGE.getValue().getMaximum()))
                .count();
        assertThat(arrayLength).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }
}