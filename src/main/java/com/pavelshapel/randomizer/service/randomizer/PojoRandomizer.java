package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.service.Utilities;
import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
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
    private final Collection<PrimitiveRandomizer<?>> primitiveRandomizers;
    private final Utilities utilities;

    @Autowired
    public PojoRandomizer(Collection<PrimitiveRandomizer<?>> primitiveRandomizers,
                          Utilities utilities) {
        this.primitiveRandomizers = primitiveRandomizers;
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
        return primitiveRandomizers.stream()
                .filter(randomizer -> utilities.getSuperClassGenericType(randomizer, 0).getName().equalsIgnoreCase(className))
                .map(Randomizer::randomize)
                .collect(utilities.toSingleton());
    }

    private Map<String, Object> createDefaultMap() {
        return primitiveRandomizers.stream()
                .collect(getPrimitiveRandomizerMapCollector());
    }

    private Collector<PrimitiveRandomizer<?>, ?, Map<String, Object>> getPrimitiveRandomizerMapCollector() {
        return Collectors
                .toMap(
                        randomizer -> utilities.getSuperClassGenericType(randomizer, 0).getSimpleName().toLowerCase(),
                        Randomizer::randomize
                );
    }
}
