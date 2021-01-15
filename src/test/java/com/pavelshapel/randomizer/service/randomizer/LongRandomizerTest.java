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

import java.util.List;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_LONG_RANGE;
import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = LongRandomizer.class)
class LongRandomizerTest {
    @Autowired
    LongRandomizer randomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnLong() {
        final Long randomLong = randomizer.randomize();

        assertThat(randomLong).isBetween(
                DEFAULT_LONG_RANGE.getValue().getMinimum(),
                DEFAULT_LONG_RANGE.getValue().getMaximum()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnLong(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Long randomLong = randomizer.randomize(range);

        assertThat(randomLong).isBetween(
                range.getMinimum(),
                range.getMaximum()
        );
    }

    @Test
    void getRandomEntity_WithDefaultRange_ShouldReturnRandomEntity() {
        final RandomEntity<Long> randomEntity = randomizer.getRandomEntity();

        assertThat(randomEntity.getValue()).isBetween(
                DEFAULT_LONG_RANGE.getValue().getMinimum(),
                DEFAULT_LONG_RANGE.getValue().getMaximum()
        );
        assertThat(randomEntity.getType()).isEqualTo(Long.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void getRandomEntity_WithBoundedRange_ShouldReturnRandomEntity(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final RandomEntity<Long> randomEntity = randomizer.getRandomEntity(range);

        assertThat(randomEntity.getValue()).isBetween(
                range.getMinimum(),
                range.getMaximum()
        );
        assertThat(randomEntity.getType()).isEqualTo(Long.class);
    }

    @Test
    void getRandomEntityList_WithDefaultRange_ShouldReturnRandomEntityList() {
        final List<RandomEntity<Long>> randomEntityList = randomizer.getRandomEntityList();

        assertThat(randomEntityList.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}