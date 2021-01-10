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
    DEFAULT_MIN_LONG(new GregorianCalendar(1900, Calendar.JANUARY, 1).getTimeInMillis()),
    DEFAULT_MAX_LONG(GregorianCalendar.getInstance().getTimeInMillis()),
    DEFAULT_MIN_POSITIVE_BYTE(1),
    DEFAULT_MAX_POSITIVE_BYTE(Byte.MAX_VALUE);

    long value;
}
