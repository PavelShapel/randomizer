package com.pavelshapel.randomizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public final class DoubleSpecification extends AbstractSpecification<Double> {
    long precision = DEFAULT_OFFSET;

    public DoubleSpecification(long min, long max) {
        super(min, max);
    }

    public DoubleSpecification(long min, long max, long precision) {
        this(min, max);
        this.precision = precision;
    }
}
