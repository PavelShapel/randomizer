package com.pavelshapel.randomizer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RandomEntity<T> {
    T value;
    Class<?> type;
}
