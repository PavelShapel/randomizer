package com.pavelshapel.randomizer.service.randomizer.array;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public final class StringArrayRandomizer extends ArrayRandomizer<String> {
    public StringArrayRandomizer() {
        super(String[].class);
    }

    @Override
    protected String[] collectionToArray(Collection<String> collection) {
        return collection.toArray(new String[0]);
    }
}
