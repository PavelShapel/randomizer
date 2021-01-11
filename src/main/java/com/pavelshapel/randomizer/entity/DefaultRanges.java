package com.pavelshapel.randomizer.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.Range;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.pavelshapel.randomizer.entity.ConstantsRange.*;

@Getter
@ToString
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public enum DefaultRanges {
    DEFAULT_LONG_RANGE(
            Range.between(
                    DEFAULT_MIN_LONG.getValue(),
                    DEFAULT_MAX_LONG.getValue()
            )
    ),
    DEFAULT_POSITIVE_BYTE_RANGE(
            Range.between(
                    DEFAULT_MIN_POSITIVE_BYTE.getValue(),
                    DEFAULT_MAX_POSITIVE_BYTE.getValue()
            )
    ),
    DEFAULT_YEAR_RANGE(
            Range.between(
                    DEFAULT_MIN_YEAR.getValue(),
                    DEFAULT_MAX_YEAR.getValue()
            )
    ),
    DEFAULT_DATE_RANGE(
            Range.between(
                    new GregorianCalendar(
                            Math.toIntExact(DEFAULT_MIN_YEAR.getValue()),
                            Calendar.JANUARY,
                            1
                    ).getTimeInMillis(),
                    new GregorianCalendar(
                            Math.toIntExact(DEFAULT_MAX_YEAR.getValue()),
                            Calendar.DECEMBER,
                            31
                    ).getTimeInMillis()
            )
    );

    Range<Long> value;
}
