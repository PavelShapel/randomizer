package com.pavelshapel.randomizer.service.randomizer.collection.map;

import com.pavelshapel.randomizer.service.randomizer.collection.CollectionRandomizer;
import com.pavelshapel.randomizer.service.randomizer.MapRandomizer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public final class MapCollectionRandomizer extends CollectionRandomizer<Map<String, Object>> implements MapRandomizer<Collection<Map<String, Object>>> {
    @Override
    public Collection<Map<String, Object>> randomize(Map<String, Object> map) {
        return randomize(map, null);
    }
}
