package com.pavelshapel.randomizer.service.randomizer.primitive;

import com.pavelshapel.randomizer.entity.specification.collection.CollectionSpecification;
import com.pavelshapel.randomizer.entity.specification.value.range.LongSpecification;
import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {
        LongRandomizer.class,
        LongSpecification.class,
        CollectionSpecification.class
})
class LongRandomizerTest {
    @Autowired
    private LongRandomizer randomizer;

    @Test
    void randomize_WithoutParams_ShouldReturnLong() {
        final Long randomValue = randomizer.randomizeValue();

        assertThat(randomValue).isNotNull();
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_RangeAsParam_ShouldReturnLong(long min, long max) {
        final Long randomValue = randomizer.randomizeBoundedValue(min, max);

        assertThat(randomValue).isNotNull();
    }

    @Test
    void randomizeCollection_WithoutParams_ShouldReturnCollection() {
        final Collection<Long> randomValue = randomizer.randomizeCollection();

        assertThat(randomValue).isNotNull();
    }
}