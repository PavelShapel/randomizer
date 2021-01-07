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
        super(DEFAULT_MIN_BYTE, DEFAULT_MAX_BYTE);
    }

    public StringSpecification(long min, long max) {
        super(getVerifiedBound(min), getVerifiedBound(max));
    }

    public static int getVerifiedBound(long bound) {
        final long verifiedMinBound = Math.max(DEFAULT_MIN_BYTE, bound);
        final long verifiedMaxBound = Math.min(DEFAULT_MAX_BYTE, verifiedMinBound);

        return Math.toIntExact(verifiedMaxBound);
    }
}
