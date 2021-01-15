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

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = StringRandomizer.class)
class StringRandomizerTest {
    @Autowired
    StringRandomizer randomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnString() {
        final String randomString = randomizer.randomize();

        assertThat(randomString.length()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void randomize_WithBoundedSpecification_ShouldReturnString(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        String randomString = randomizer.randomize(range);

        assertThat(randomString.length()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }

    @Test
    void getRandomEntity_WithDefaultRange_ShouldReturnRandomEntity() {
        final RandomEntity<String> randomEntity = randomizer.getRandomEntity();

        assertThat(randomEntity.getValue().length()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
        assertThat(randomEntity.getType()).isEqualTo(String.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void getRandomEntity_WithBoundedRange_ShouldReturnRandomEntity(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final RandomEntity<String> randomEntity = randomizer.getRandomEntity(range);

        assertThat(randomEntity.getValue().length()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
        assertThat(randomEntity.getType()).isEqualTo(String.class);
    }

    @Test
    void getRandomEntityList_WithDefaultRange_ShouldReturnRandomEntityList() {
        final List<RandomEntity<String>> randomEntityList = randomizer.getRandomEntityList();

        assertThat(randomEntityList.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}