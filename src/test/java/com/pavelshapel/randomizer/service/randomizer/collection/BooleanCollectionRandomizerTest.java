package com.pavelshapel.randomizer.service.randomizer.collection;

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

import java.util.Collection;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {
        BooleanCollectionRandomizer.class,
        BooleanPrimitiveRandomizer.class
}
)
class BooleanCollectionRandomizerTest {
    @Autowired
    private BooleanCollectionRandomizer collectionRandomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnCollection() {
        final Collection<Boolean> randomCollection = collectionRandomizer.randomize();

        final long collectionSize = randomCollection.stream()
                .peek(value -> assertThat(value).isInstanceOf(Boolean.class))
                .peek(value -> assertThat(value).isIn(false,true))
                .count();
        assertThat(collectionSize).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedSizeRange_ShouldReturnCollection(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Collection<Boolean> randomCollection = collectionRandomizer.randomize(range);

        final long collectionSize = randomCollection.stream()
                .peek(value -> assertThat(value).isInstanceOf(Boolean.class))
                .peek(value -> assertThat(value).isIn(false,true))
                .count();
        assertThat(collectionSize).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }

    @Test
    void randomize_NullAsParam_ShouldReturnCollection() {
        final Collection<Boolean> randomCollection = collectionRandomizer.randomize(null);

        final long collectionSize = randomCollection.stream()
                .peek(value -> assertThat(value).isInstanceOf(Boolean.class))
                .peek(value -> assertThat(value).isIn(false,true))
                .count();
        assertThat(collectionSize).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(FourParametersLongProvider.class)
    void randomize_WithBoundedValueSizeRange_ShouldReturnCollection(long minValue, long maxValue, long minSize, long maxSize) {
        final Range<Long> rangeValue = Range.between(minValue, maxValue);
        final Range<Long> rangeSize = Range.between(minSize, maxSize);

        final Collection<Boolean> randomCollection = collectionRandomizer.randomize(rangeValue, rangeSize);

        final long collectionSize = randomCollection.stream()
                .peek(value -> assertThat(value).isInstanceOf(Boolean.class))
                .peek(value -> assertThat(value).isIn(false,true))
                .count();
        assertThat(collectionSize).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }
}