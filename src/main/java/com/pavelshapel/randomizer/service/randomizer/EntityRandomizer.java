package com.pavelshapel.randomizer.service.randomizer;

import com.pavelshapel.randomizer.entity.Entity;

public interface EntityRandomizer<T> {
    T randomize(Entity entity);
}
