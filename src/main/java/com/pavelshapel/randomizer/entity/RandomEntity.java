package com.pavelshapel.randomizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class RandomEntity<T> {
    T value;
    Class<T> type;
}


