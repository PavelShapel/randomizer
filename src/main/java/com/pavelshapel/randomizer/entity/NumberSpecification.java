package com.pavelshapel.randomizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class NumberSpecification {
    public static final int DEFAULT_MIN = Integer.MIN_VALUE;
    public static final int DEFAULT_MAX = Integer.MAX_VALUE;
    public static final int DEFAULT_PRECISION = 2;

    int min = DEFAULT_MIN;
    int max = DEFAULT_MAX;
    int precision = DEFAULT_PRECISION;

    public NumberSpecification(int min, int max) {
        this.min = Math.min(min, max);
        this.max = Math.max(min, max);
    }

    public NumberSpecification(int min, int max, int precision) {
        this(min, max);
        this.precision = precision;
    }
}
