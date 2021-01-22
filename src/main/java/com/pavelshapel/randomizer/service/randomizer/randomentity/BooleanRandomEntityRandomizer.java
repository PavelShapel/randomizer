package com.pavelshapel.randomizer.service.randomizer.randomentity;

import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class BooleanRandomEntityRandomizer extends RandomEntityRandomizer<Boolean> {

    @Autowired
    public BooleanRandomEntityRandomizer(PrimitiveRandomizer<Boolean> primitiveRandomizer) {
        super(primitiveRandomizer);
    }
}
