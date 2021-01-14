package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.provider.TwoLongProvider;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_DATE_RANGE;
import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
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
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnDate(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Date randomDate = randomizer.randomize(range);

        assertThat(randomDate).isInstanceOf(Date.class);
    }

    @Test
    void getRandomEntity_WithDefaultRange_ShouldReturnRandomEntity() {
        final RandomEntity<Date> randomEntity = randomizer.getRandomEntity();

        assertThat(randomEntity.getValue().getTime()).isBetween(
                DEFAULT_DATE_RANGE.getValue().getMinimum(),
                DEFAULT_DATE_RANGE.getValue().getMaximum()
        );
        assertThat(randomEntity.getType()).isEqualTo(Date.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void getRandomEntity_WithBoundedRange_ShouldReturnRandomEntity(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final RandomEntity<Date> randomEntity = randomizer.getRandomEntity(range);

        assertThat(randomEntity.getType()).isEqualTo(Date.class);
    }

    @Test
    void getRandomEntityList_WithDefaultRange_ShouldReturnRandomEntity() {
        final List<RandomEntity<Date>> randomEntityList = randomizer.getRandomEntityList();

        assertThat(randomEntityList.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}