package com.pavelshapel.randomizer.service;

import com.pavelshapel.randomizer.entity.AbstractSpecification;
import lombok.NonNull;

public interface BoundedRandomizer<T> extends Randomizer<T> {
    T randomize(@NonNull AbstractSpecification<T> specification);
}
