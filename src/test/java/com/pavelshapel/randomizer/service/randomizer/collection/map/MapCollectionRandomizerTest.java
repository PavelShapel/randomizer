package com.pavelshapel.randomizer.service.randomizer.collection.map;

import com.pavelshapel.randomizer.provider.FourParametersLongProvider;
import com.pavelshapel.randomizer.provider.PrimitiveTypeProvider;
import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
import com.pavelshapel.randomizer.service.randomizer.MapRandomizer;
import com.pavelshapel.randomizer.service.randomizer.Randomizer;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MapCollectionRandomizerTest {
    @Autowired
    private MapCollectionRandomizer collectionRandomizer;
    @Autowired
    private List<Randomizer<?>> randomizers;

    @BeforeEach
    void setUp() {
        randomizers.removeIf(randomizer -> randomizer instanceof MapRandomizer);
    }

    @Test
    void randomize_WithDefaultRange_ShouldReturnCollection() {
        final Collection<Map<String, Object>> randomCollection = collectionRandomizer.randomize();

        final long collectionSize = randomCollection.stream()
                .peek(value -> assertThat(value).isInstanceOf(Map.class))
                .peek(value -> assertThat(value.size()).isSameAs(randomizers.size()))
                .count();
        assertThat(collectionSize).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(TwoParametersLongProvider.class)
    void randomize_WithBoundedSizeRange_ShouldReturnCollection(long min, long max) {
        final Range<Long> range = Range.between(min, max);

        final Collection<Map<String, Object>> randomCollection = collectionRandomizer.randomize(range);

        final long collectionSize = randomCollection.stream()
                .peek(value -> assertThat(value).isInstanceOf(Map.class))
                .peek(value -> assertThat(value.size()).isSameAs(randomizers.size()))
                .count();
        assertThat(collectionSize).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(FourParametersLongProvider.class)
    void randomize_WithBoundedValueSizeRange_ShouldReturnCollection(long minValue, long maxValue, long minSize, long maxSize) {
        final Range<Long> rangeValue = Range.between(minValue, maxValue);
        final Range<Long> rangeSize = Range.between(minSize, maxSize);

        final Collection<Map<String, Object>> randomCollection = collectionRandomizer.randomize(rangeValue, rangeSize);

        final long collectionSize = randomCollection.stream()
                .peek(value -> assertThat(value).isInstanceOf(Map.class))
                .peek(value -> assertThat(value.size()).isSameAs(randomizers.size()))
                .count();
        assertThat(collectionSize).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }

    @ParameterizedTest
    @ArgumentsSource(PrimitiveTypeProvider.class)
    void randomize_MapAsParam_ShouldReturnMap(Class<?> targetClass) {
        final Map<String, Object> map = createMap(targetClass);

        final Collection<Map<String, Object>> randomMapCollection = collectionRandomizer.randomize(map);

        final long collectionSize = randomMapCollection.stream()
                .peek(value -> assertThat(value).isInstanceOf(Map.class))
                .peek(value -> assertThat(value).hasSameSizeAs(map))
                .peek(value -> assertThat(value.get("object")).isInstanceOf(targetClass))
                .count();
        assertThat(collectionSize).isBetween(
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
        );
    }

    private Map<String, Object> createMap(Class<?> targetClass) {
        Map<String, Object> map = new HashMap<>();
        map.put("object", targetClass.getSimpleName().toLowerCase());
        return map;
    }
}