package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.service.Utilities;
import com.pavelshapel.randomizer.service.randomizer.collection.CollectionRandomizer;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public final class PojoRandomizer implements Randomizer<Map<String, Object>> {
    private final Collection<Randomizer<?>> randomizers;
    private final Utilities utilities;

    @Autowired
    public PojoRandomizer(Collection<Randomizer<?>> randomizers,
                          Utilities utilities) {
        this.randomizers = randomizers;
        this.utilities = utilities;
    }

    @Override
    public Map<String, Object> randomize() {
        return createDefaultMap();
    }

    @Override
    public Map<String, Object> randomize(Range<Long> range) {
        return createDefaultMap();
    }

    public Map<String, Object> randomize(Map<String, Object> pojo) {
        final Map<String, Object> map = new HashMap<>();
        pojo.forEach((key, value) -> map.put(key, getRandomValueByClassName(value.toString())));

        return map;
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
