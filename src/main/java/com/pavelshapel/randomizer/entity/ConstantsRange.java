package com.pavelshapel.randomizer.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Getter
@ToString
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public enum ConstantsRange {
    DEFAULT_MIN_YEAR(1900),
    DEFAULT_MAX_YEAR(GregorianCalendar.getInstance().get(Calendar.YEAR)),

    DEFAULT_MIN_LONG(new GregorianCalendar(
            Math.toIntExact(DEFAULT_MIN_YEAR.getValue()),
            Calendar.JANUARY,
            1).getTimeInMillis()
    ),
    DEFAULT_MAX_LONG(new GregorianCalendar(
            Math.toIntExact(DEFAULT_MAX_YEAR.getValue()),
            Calendar.DECEMBER,
            31).getTimeInMillis()
    ),

    DEFAULT_MIN_POSITIVE_BYTE(1),
    DEFAULT_MAX_POSITIVE_BYTE(Byte.MAX_VALUE);

    long value;
}
