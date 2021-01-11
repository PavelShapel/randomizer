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

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_DATE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = DateRandomizer.class)
class DateRandomizerTest {
    @Autowired
    DateRandomizer randomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnDate() {
        final Date randomDate = randomizer.randomize();

        assertThat(randomDate.getTime()).isBetween(
                DEFAULT_DATE_RANGE.getValue().getMinimum(),
                DEFAULT_DATE_RANGE.getValue().getMaximum()
        );
        assertThat(randomizer.getGenericParameterClass()).isEqualTo(Date.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnDate(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Date randomDate = randomizer.randomize(range);

        assertThat(randomDate).isInstanceOf(Date.class);
    }
}