package com.pavelshapel.randomizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public final class LongSpecification extends AbstractSpecification<Long> {
    public LongSpecification(long min, long max) {
        super(min, max);
    }
}
