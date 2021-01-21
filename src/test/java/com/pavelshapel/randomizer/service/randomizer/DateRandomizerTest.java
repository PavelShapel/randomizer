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
import java.util.Date;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_LONG_RANGE;
import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = DateRandomizer.class)
class DateRandomizerTest {
    @Autowired
    DateRandomizer dateRandomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnDate() {
        final Date randomDate = dateRandomizer.randomize();

        assertThat(randomDate.getTime()).isBetween(
                DEFAULT_LONG_RANGE.getValue().getMinimum(),
                DEFAULT_LONG_RANGE.getValue().getMaximum()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnDate(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Date randomDate = dateRandomizer.randomize(range);

        assertThat(randomDate).isInstanceOf(Date.class);
    }

    @Test
    void randomizeCollection_ShouldReturnCollection() {
        final Collection<Date> randomCollection = dateRandomizer.randomizeCollection();

        assertThat(randomCollection.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}