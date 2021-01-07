package com.pavelshapel.randomizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Getter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public abstract class AbstractSpecification<T> {
    public static final long DEFAULT_MIN_LONG = new GregorianCalendar(1900, Calendar.JANUARY, 1).getTimeInMillis();
    public static final long DEFAULT_MAX_LONG = GregorianCalendar.getInstance().getTimeInMillis();
    public static final byte DEFAULT_MIN_BYTE = 2;
    public static final byte DEFAULT_MAX_BYTE = Byte.MAX_VALUE;

    final Class<T> genericParameterClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    long min = DEFAULT_MIN_LONG;
    long max = DEFAULT_MAX_LONG;

    public AbstractSpecification(long min, long max) {
        this.min = Math.min(min, max);
        this.max = Math.max(min, max);
    }
}
