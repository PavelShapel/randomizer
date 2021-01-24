package com.pavelshapel.randomizer.service.randomizer.primitive.map;

import com.pavelshapel.randomizer.service.Utilities;
import com.pavelshapel.randomizer.service.randomizer.Randomizer;
import com.pavelshapel.randomizer.service.randomizer.collection.CollectionRandomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public final class MapPrimitiveRandomizer extends PrimitiveRandomizer<Map<String, Object>> implements MapRandomizer<Map<String, Object>> {
    @Autowired
    private Collection<Randomizer<?>> randomizers;
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

    @Override
    public Map<String, Object> randomize(Map<String, Object> map) {
        final Map<String, Object> result = new HashMap<>();
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
        return randomizer instanceof CollectionRandomizer
                ? String.format("%s[]", superClassGenericType.getSimpleName())
                : superClassGenericType.getSimpleName();

    }

    private Map<String, Object> createDefaultMap() {
        return randomizers.stream()
                .collect(getPrimitiveRandomizerMapCollector());
    }

    private Collector<Randomizer<?>, ?, Map<String, Object>> getPrimitiveRandomizerMapCollector() {
        return Collectors
                .toMap(
                        randomizer -> getSuperClassGenericTypeName(randomizer).toLowerCase(),
                        Randomizer::randomize
                );
    }
}
