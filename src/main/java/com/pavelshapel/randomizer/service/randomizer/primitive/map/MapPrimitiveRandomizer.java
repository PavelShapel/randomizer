package com.pavelshapel.randomizer.service.randomizer.primitive.map;

import com.pavelshapel.randomizer.service.Utilities;
import com.pavelshapel.randomizer.service.randomizer.MapRandomizer;
import com.pavelshapel.randomizer.service.randomizer.Randomizer;
import com.pavelshapel.randomizer.service.randomizer.collection.CollectionRandomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public final class MapPrimitiveRandomizer extends PrimitiveRandomizer<Map<String, Object>> implements MapRandomizer<Map<String, Object>> {
    @Autowired
    private List<Randomizer<?>> randomizers;
    @Autowired
    private Utilities utilities;

    @PostConstruct
    private void postConstruct() {
        randomizers.removeIf(randomizer -> randomizer instanceof MapRandomizer);
    }

    @Override
    public Map<String, Object> randomize() {
        return createDefaultMap();
    }

    @Override
    protected Map<String, Object> implementRandomization(Range<Long> range) {
        return createDefaultMap();
    }

    private Map<String, Object> createDefaultMap() {
        final Map<String, Object> notSortedMap = randomizers.stream().collect(getMapCollector());
        final TreeMap<String, Object> sortedMap = new TreeMap<>(Comparator.naturalOrder());

        sortedMap.putAll(notSortedMap);

        return sortedMap;
    }

    private Collector<Randomizer<?>, ?, Map<String, Object>> getMapCollector() {
        return Collectors.toMap(
                this::getSuperClassGenericTypeName,
                Randomizer::randomize
        );
    }

    @Override
    public Map<String, Object> randomize(Map<String, Object> map) {
        final Map<String, Object> result = new TreeMap<>(Comparator.naturalOrder());
        map.forEach((key, value) -> result.put(key, getRandomValueByClassName(value.toString())));

        return result;
    }

    private Object getRandomValueByClassName(String className) {
        return randomizers.stream()
                .filter(randomizer -> className.equalsIgnoreCase(getSuperClassGenericTypeName(randomizer)))
                .map(Randomizer::randomize)
                .collect(utilities.toSingleton());
    }

    private String getSuperClassGenericTypeName(Randomizer<?> randomizer) {
        final Class<?> superClassGenericType = utilities.getSuperClassGenericType(randomizer, 0);
        final String superClassGenericTypeName = superClassGenericType.getSimpleName().toLowerCase();

        return randomizer instanceof CollectionRandomizer
                ? String.format("%s[]", superClassGenericTypeName)
                : superClassGenericTypeName;

    }
}
