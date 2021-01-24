package com.pavelshapel.randomizer.service.randomizer.primitive;

import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_YEAR_RANGE;

@Service
public final class DatePrimitiveRandomizer extends PrimitiveRandomizer<Date> {
    @Override
    public Date randomize() {
        return randomize(DEFAULT_YEAR_RANGE.getValue());
    }

    @Override
    protected Date implementRandomization(Range<Long> range) {
        final Range<Calendar> dateRange = getDateRange(range);
        final long randomLong = ThreadLocalRandom.current().nextLong(
                dateRange.getMinimum().getTimeInMillis(),
                dateRange.getMaximum().getTimeInMillis()
        );

        return new Date(randomLong);
    }

    private Range<Calendar> getDateRange(Range<Long> range) {
        final Range<Long> intersectionWithYearRange = DEFAULT_YEAR_RANGE.getValue().intersectionWith(range);
        final Calendar min = new GregorianCalendar(intersectionWithYearRange.getMinimum().intValue(), Calendar.JANUARY, 1);
        final Calendar max = new GregorianCalendar(intersectionWithYearRange.getMaximum().intValue(), Calendar.DECEMBER, 31);

        return Range.between(min, max);
    }
}
