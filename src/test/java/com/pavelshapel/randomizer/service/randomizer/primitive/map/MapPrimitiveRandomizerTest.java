package com.pavelshapel.randomizer.service.randomizer.primitive.map;

import com.pavelshapel.randomizer.provider.PrimitiveTypeProvider;
import com.pavelshapel.randomizer.service.randomizer.MapRandomizer;
import com.pavelshapel.randomizer.service.randomizer.Randomizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MapPrimitiveRandomizerTest {
    @Autowired
    private MapPrimitiveRandomizer mapPrimitiveRandomizer;
    @Autowired
    private List<Randomizer<?>> randomizers;

    @BeforeEach
    void setUp() {
        randomizers.removeIf(randomizer -> randomizer instanceof MapRandomizer);
    }

    @Test
    void randomize_WithDefaultRange_ShouldReturnMap() {
        final Map<String, Object> randomMap = mapPrimitiveRandomizer.randomize();

        assertThat(randomMap).isNotNull();
        assertThat(randomMap.size()).isSameAs(randomizers.size());
    }

    @Test
    void randomize_WithBoundedRange_ShouldReturnMap() {
        final Map<String, Object> randomMap =
                mapPrimitiveRandomizer.randomize(DEFAULT_POSITIVE_BYTE_RANGE.getValue());

        assertThat(randomMap).isNotNull();
        assertThat(randomMap.size()).isSameAs(randomizers.size());
    }

    @ParameterizedTest
    @ArgumentsSource(PrimitiveTypeProvider.class)
    void randomize_MapAsParam_ShouldReturnMap(Class<?> targetClass) {
        final Map<String, Object> map = createMap(targetClass);

        final Map<String, Object> randomMap = mapPrimitiveRandomizer.randomize(map);

        assertThat(randomMap).isNotNull();
        assertThat(randomMap.size()).isSameAs(map.size());
        assertThat(randomMap.values().toArray()[0]).isInstanceOf(targetClass);
    }

    private Map<String, Object> createMap(Class<?> targetClass) {
        Map<String, Object> map = new HashMap<>();
        map.put("object", targetClass.getSimpleName().toLowerCase());
        return map;
    }

}