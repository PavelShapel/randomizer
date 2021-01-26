package com.pavelshapel.randomizer.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Date;
import java.util.stream.Stream;

public class PrimitiveTypeProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(Long.class, Double.class, String.class, Boolean.class, Date.class,
                Long[].class, Double[].class).map(Arguments::of);
    }
}