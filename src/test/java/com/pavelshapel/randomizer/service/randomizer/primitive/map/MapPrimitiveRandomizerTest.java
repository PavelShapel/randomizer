package com.pavelshapel.randomizer.service.randomizer.primitive.map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MapPrimitiveRandomizerTest {
    @Autowired
    MapPrimitiveRandomizer mapPrimitiveRandomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnMap() {
        final Map<String, Object> randomMap = mapPrimitiveRandomizer.randomize();

        assertThat(randomMap).isNotNull();
    }

    @Test
    void randomize_WithBoundedRange_ShouldReturnMap() {
        final Map<String, Object> randomMap =
                mapPrimitiveRandomizer.randomize(DEFAULT_POSITIVE_BYTE_RANGE.getValue());

        assertThat(randomMap).isNotNull();
    }

}