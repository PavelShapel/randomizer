package com.pavelshapel.randomizer.service.randomizer.collection;

import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class StringCollectionRandomizer extends CollectionRandomizer<String> {
    @Autowired
    public StringCollectionRandomizer(PrimitiveRandomizer<String> primitiveRandomizer) {
        super(primitiveRandomizer);
    }
}
