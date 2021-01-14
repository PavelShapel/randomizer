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
@ContextConfiguration(classes = BooleanRandomizer.class)
class BooleanRandomizerTest {
    @Autowired
    BooleanRandomizer randomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnBoolean() {
        final Boolean randomBoolean = randomizer.randomize();

        assertThat(randomBoolean).isIn(false, true);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnBoolean(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Boolean randomBoolean = randomizer.randomize(range);

        assertThat(randomBoolean).isIn(false, true);
    }

    @Test
    void getRandomEntity_WithDefaultRange_ShouldReturnRandomEntity() {
        final RandomEntity<Boolean> randomEntity = randomizer.getRandomEntity();

        assertThat(randomEntity.getValue()).isIn(false, true);
        assertThat(randomEntity.getType()).isEqualTo(Boolean.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoLongProvider.class)
    void getRandomEntity_WithBoundedRange_ShouldReturnRandomEntity(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final RandomEntity<Boolean> randomEntity = randomizer.getRandomEntity(range);

        assertThat(randomEntity.getValue()).isIn(false, true);
        assertThat(randomEntity.getType()).isEqualTo(Boolean.class);
    }

    @Test
    void getRandomEntityList_WithDefaultRange_ShouldReturnRandomEntity() {
        final List<RandomEntity<Boolean>> randomEntityList = randomizer.getRandomEntityList();

        assertThat(randomEntityList.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}