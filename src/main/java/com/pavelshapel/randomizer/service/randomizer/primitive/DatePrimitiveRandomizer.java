//package com.pavelshapel.randomizer.service.randomizer.primitive;
//
//import com.pavelshapel.randomizer.entity.specification.value.AbstractValueSpecification;
//import com.pavelshapel.randomizer.service.randomizer.Randomizer;
//import org.apache.commons.lang3.Range;
//import org.springframework.stereotype.Service;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.concurrent.ThreadLocalRandom;
//
//@Service
//public final class DatePrimitiveRandomizer implements Randomizer<Date> {
//    @Override
//    public Date randomize(AbstractValueSpecification<Date> valueSpecification) {
//        final Range<Calendar> dateRange = getDateRange(valueSpecification);
//        final long randomLong = ThreadLocalRandom.current().nextLong(
//                dateRange.getMinimum().getTimeInMillis(),
//                dateRange.getMaximum().getTimeInMillis()
//        );
//
//        return new Date(randomLong);
//    }
//
//    private Range<Calendar> getDateRange(AbstractValueSpecification<Date> valueSpecification) {
//        final Calendar min = new GregorianCalendar(
//                valueSpecification.getRange().getMinimum().intValue(),
//                Calendar.JANUARY,
//                1);
//        final Calendar max = new GregorianCalendar(
//                valueSpecification.getRange().getMaximum().intValue(),
//                Calendar.DECEMBER,
//                31);
//
//        return Range.between(min, max);
//    }
//}
