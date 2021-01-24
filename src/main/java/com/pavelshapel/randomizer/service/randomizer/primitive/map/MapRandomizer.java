package com.pavelshapel.randomizer.service.randomizer.primitive.map;

import java.util.Map;

public interface MapRandomizer<T> {
    T randomize(Map<String, Object> map);
}
