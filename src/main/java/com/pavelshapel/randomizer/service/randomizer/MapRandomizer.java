package com.pavelshapel.randomizer.service.randomizer;

import java.util.Map;

public interface MapRandomizer<T> {
    T randomize(Map<String, Object> map);
}
