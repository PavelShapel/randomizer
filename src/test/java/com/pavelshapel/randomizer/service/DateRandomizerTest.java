package com.pavelshapel.randomizer.service;

import com.pavelshapel.randomizer.provider.TwoLongProvider;
import com.pavelshapel.randomizer.service.randomizer.DateRandomizer;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_LONG_RANGE;
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
        assertThat(dateRandomizer.getGenericParameterClass()).isEqualTo(Date.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnDate(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Date randomDate = dateRandomizer.randomize(range);

        assertThat(randomDate.getTime()).isBetween(
                range.getMinimum(),
                range.getMaximum()
        );
    }
}