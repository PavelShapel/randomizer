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
    public static final long DEFAULT_MIN = new GregorianCalendar(1900, Calendar.JANUARY, 1).getTimeInMillis();
    public static final long DEFAULT_MAX = GregorianCalendar.getInstance().getTimeInMillis();
    public static final long DEFAULT_OFFSET = 2;

    final Class<T> genericParameterClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    long min = DEFAULT_MIN;
    long max = DEFAULT_MAX;

    public AbstractSpecification(long min, long max) {
        this.min = Math.min(min, max);
        this.max = Math.max(min, max);
    }
}
