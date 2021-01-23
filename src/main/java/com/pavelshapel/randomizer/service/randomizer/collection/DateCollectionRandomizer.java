package com.pavelshapel.randomizer.service.randomizer.collection;

import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public final class DateCollectionRandomizer extends CollectionRandomizer<Date> {
    @Autowired
    public DateCollectionRandomizer(PrimitiveRandomizer<Date> primitiveRandomizer) {
        super(primitiveRandomizer);
    }
}
