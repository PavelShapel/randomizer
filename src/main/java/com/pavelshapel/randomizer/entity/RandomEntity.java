package com.pavelshapel.randomizer.entity;

import lombok.Value;

@Value
public class RandomEntity<T> {
    T value;
    Class<T> type;
}


