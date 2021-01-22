package com.pavelshapel.randomizer.service.randomizer.randomentity;

import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class DoubleRandomEntityRandomizer extends RandomEntityRandomizer<Double> {

    @Autowired
    public DoubleRandomEntityRandomizer(PrimitiveRandomizer<Double> primitiveRandomizer) {
        super(primitiveRandomizer);
    }
}
