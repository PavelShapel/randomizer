package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PojoRandomizerTest {
    private final PojoRandomizer pojoRandomizer;

    @Autowired
    public PojoRandomizerTest(PojoRandomizer pojoRandomizer) {
        this.pojoRandomizer = pojoRandomizer;
    }

    @Test
    void randomize_WithDefaultRange_ShouldReturnPojo() {
        final Map<String, Object> randomMap = pojoRandomizer.randomize();

        assertThat(randomMap).isNotNull();
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedRange_ShouldReturnPojo(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Map<String, Object> randomMap = pojoRandomizer.randomize(range);

        assertThat(randomMap).isNotNull();
    }
}