package com.pavelshapel.randomizer.service.randomizer.array;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public final class DateArrayRandomizer extends ArrayRandomizer<Date> {
    public DateArrayRandomizer() {
        super(Date[].class);
    }

    @Override
    protected Date[] collectionToArray(Collection<Date> collection) {
        return collection.toArray(new Date[0]);
    }
}
