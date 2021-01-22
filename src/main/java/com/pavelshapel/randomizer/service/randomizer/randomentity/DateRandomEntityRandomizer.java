package com.pavelshapel.randomizer.service.randomizer.randomentity;

import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public final class DateRandomEntityRandomizer extends RandomEntityRandomizer<Date> {

    @Autowired
    public DateRandomEntityRandomizer(PrimitiveRandomizer<Date> primitiveRandomizer) {
        super(primitiveRandomizer);
    }
}
