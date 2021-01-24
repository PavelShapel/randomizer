package com.pavelshapel.randomizer.service.randomizer.collection;

import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
import com.pavelshapel.randomizer.service.randomizer.primitive.DatePrimitiveRandomizer;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collection;
import java.util.Date;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {
        DateCollectionRandomizer.class,
        DatePrimitiveRandomizer.class
}
)
class DateCollectionRandomizerTest {
    @Autowired
    private DateCollectionRandomizer dateCollectionRandomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnCollection() {
        final Collection<Date> randomCollection = dateCollectionRandomizer.randomize();

        assertThat(randomCollection.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnCollection(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Collection<Date> randomCollection = dateCollectionRandomizer.randomize(range);

        assertThat(randomCollection.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }

    @Test
    void randomize_NullAsParam_ShouldReturnCollection() {
        final Collection<Date> randomCollection = dateCollectionRandomizer.randomize(null);

        assertThat(randomCollection.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}