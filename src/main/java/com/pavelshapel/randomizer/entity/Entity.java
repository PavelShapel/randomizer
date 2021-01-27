package com.pavelshapel.randomizer.entity;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Entity extends TreeMap<String, Object> {
    public Entity() {
        super(Comparator.naturalOrder());
    }

    public Entity(Map<? extends String, ?> map) {
        this();
        this.putAll(map);
    }

    public Entity(String key, Object value) {
        this();
        this.put(key, value);
    }
}
