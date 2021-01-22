package com.pavelshapel.randomizer.service.randomizer.randomentity;

import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
import com.pavelshapel.randomizer.service.randomizer.primitive.BooleanPrimitiveRandomizer;
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
        BooleanRandomEntityRandomizer.class,
        BooleanPrimitiveRandomizer.class})
class BooleanRandomEntityRandomizerTest {
    private final BooleanRandomEntityRandomizer booleanRandomEntityRandomizer;

    @Autowired
    BooleanRandomEntityRandomizerTest(BooleanRandomEntityRandomizer booleanRandomEntityRandomizer) {
        this.booleanRandomEntityRandomizer = booleanRandomEntityRandomizer;
    }

    @Test
    void randomize_WithDefaultRange_ShouldReturnRandomEntity() {
        final RandomEntity<Boolean> randomEntity = booleanRandomEntityRandomizer.randomize();

        assertThat(randomEntity.getValue()).isIn(false, true);
        assertThat(randomEntity.getType()).isEqualTo(Boolean.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnRandomEntity(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final RandomEntity<Boolean> randomEntity = booleanRandomEntityRandomizer.randomize(range);

        assertThat(randomEntity.getValue()).isIn(false, true);
        assertThat(randomEntity.getType()).isEqualTo(Boolean.class);
    }

    @Test
    void randomizeCollection_ShouldReturnCollection() {
        final Collection<RandomEntity<Boolean>> randomEntityCollection = booleanRandomEntityRandomizer.randomizeCollection();

        assertThat(randomEntityCollection.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}