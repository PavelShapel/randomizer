package com.pavelshapel.randomizer.service.randomizer.randomentity;

import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class LongRandomEntityRandomizer extends RandomEntityRandomizer<Long> {

    @Autowired
    public LongRandomEntityRandomizer(PrimitiveRandomizer<Long> primitiveRandomizer) {
        super(primitiveRandomizer);
    }
}
