package com.pavelshapel.randomizer.service.randomizer.collection;

import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
import com.pavelshapel.randomizer.service.randomizer.primitive.DoublePrimitiveRandomizer;
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
        DoubleCollectionRandomizer.class,
        DoublePrimitiveRandomizer.class
}
)
class DoubleCollectionRandomizerTest {
    private final DoubleCollectionRandomizer doubleCollectionRandomizer;

    @Autowired
    DoubleCollectionRandomizerTest(DoubleCollectionRandomizer doubleCollectionRandomizer) {
        this.doubleCollectionRandomizer = doubleCollectionRandomizer;
    }

    @Test
    void randomize_WithDefaultRange_ShouldReturnCollection() {
        final Collection<Double> randomCollection = doubleCollectionRandomizer.randomize();

        assertThat(randomCollection.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnCollection(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Collection<Double> randomCollection = doubleCollectionRandomizer.randomize(range);

        assertThat(randomCollection.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }

    @Test
    void randomize_NullAsParam_ShouldReturnCollection() {
        final Collection<Double> randomCollection = doubleCollectionRandomizer.randomize(null);

        assertThat(randomCollection.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}