package com.pavelshapel.randomizer.service.randomizer.randomentity;

import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
import com.pavelshapel.randomizer.service.Utilities;
import com.pavelshapel.randomizer.service.randomizer.primitive.StringPrimitiveRandomizer;
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
        StringRandomEntityRandomizer.class,
        StringPrimitiveRandomizer.class,
        Utilities.class})
class StringRandomEntityRandomizerTest {
    private final StringRandomEntityRandomizer stringRandomEntityRandomizer;

    @Autowired
    StringRandomEntityRandomizerTest(StringRandomEntityRandomizer stringRandomEntityRandomizer) {
        this.stringRandomEntityRandomizer = stringRandomEntityRandomizer;
    }

    @Test
    void randomize_WithDefaultRange_ShouldReturnRandomEntity() {
        final RandomEntity<String> randomEntity = stringRandomEntityRandomizer.randomize();

        assertThat(randomEntity.getValue().length()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
        assertThat(randomEntity.getType()).isEqualTo(String.class);
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnRandomEntity(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final RandomEntity<String> randomEntity = stringRandomEntityRandomizer.randomize(range);

        assertThat(randomEntity.getValue().length()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
        assertThat(randomEntity.getType()).isEqualTo(String.class);
    }

    @Test
    void randomizeCollection_ShouldReturnCollection() {
        final Collection<RandomEntity<String>> randomEntityCollection = stringRandomEntityRandomizer.randomizeCollection();

        assertThat(randomEntityCollection.size()).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum().intValue(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum().intValue()
        );
    }
}