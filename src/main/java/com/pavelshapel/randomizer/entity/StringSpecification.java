package com.pavelshapel.randomizer.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class StringSpecification extends AbstractSpecification<String> {
    public StringSpecification() {
        this(DEFAULT_OFFSET, DEFAULT_MAX);
    }

    public StringSpecification(long min, long max) {
        super(Math.max(DEFAULT_OFFSET, min), Math.min(Integer.MAX_VALUE, max));
    }
}
