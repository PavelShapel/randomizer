package com.pavelshapel.randomizer.entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class RandomEntity<T> {
    @NonNull
    T value;
    @NonNull
    Class<?> type;
}
