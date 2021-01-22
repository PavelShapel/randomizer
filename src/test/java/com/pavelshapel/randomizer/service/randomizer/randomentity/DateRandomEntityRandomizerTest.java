package com.pavelshapel.randomizer.service.randomizer.randomentity;

import com.pavelshapel.randomizer.entity.RandomEntity;
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

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_LONG_RANGE;
import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {
        DateRandomEntityRandomizer.class,
        DatePrimitiveRandomizer.class})
class DateRandomEntityRandomizerTest {
    private final DateRandomEntityRandomizer dateRandomEntityRandomizer;

    @Autowired
    DateRandomEntityRandomizerTest(DateRandomEntityRandomizer dateRandomEntityRandomizer) {
        this.dateRandomEntityRandomizer = dateRandomEntityRandomizer;
    }

    @Test
    void randomize_WithDefaultRange_ShouldReturnRandomEntity() {
        final RandomEntity<Date> randomEntity = dateRandomEntityRandomizer.randomize();

        assertThat(randomEntity.getValue().getTime()).isBetween(
                DEFAULT_LONG_RANGE.getValue().getMinimum(),
                DEFAULT_LONG_RANGE.getValue().getMaximum()
        );
        assertThat(randomEntity.getType()).isEqualTo(Date.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnRandomEntity(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final RandomEntity<Date> randomEntity = dateRandomEntityRandomizer.randomize(range);

        assertThat(randomEntity.getValue().getTime()).isBetween(
                DEFAULT_LONG_RANGE.getValue().getMinimum(),
                DEFAULT_LONG_RANGE.getValue().getMaximum()
        );
        assertThat(randomEntity.getType()).isEqualTo(Date.class);
    }

    @Test
    void randomizeCollection_ShouldReturnCollection() {
        final Collection<RandomEntity<Date>> randomEntityCollection = dateRandomEntityRandomizer.randomizeCollection();

        assertThat(randomEntityCollection.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}