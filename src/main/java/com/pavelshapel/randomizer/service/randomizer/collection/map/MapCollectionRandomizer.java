package com.pavelshapel.randomizer.service.randomizer.collection.map;

import com.pavelshapel.randomizer.service.randomizer.collection.CollectionRandomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.map.MapRandomizer;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public final class MapCollectionRandomizer extends CollectionRandomizer<Map<String, Object>> implements MapRandomizer<Map<String, Object>> {
    @Override
    public Map<String, Object> randomize(Map<String, Object> map) {
        return null;
    }
}
