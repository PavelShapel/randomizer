package com.pavelshapel.randomizer.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public abstract class LongProvider implements ArgumentsProvider {
    private final int argumentsCount;
    private int iterationsCount = 10;

    public LongProvider(int argumentsCount) {
        this.argumentsCount = argumentsCount;
    }

    public LongProvider(int argumentsCount, int iterationsCount) {
        this.argumentsCount = argumentsCount;
        this.iterationsCount = iterationsCount;
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return provideLongArguments(argumentsCount, iterationsCount);
    }

    private Stream<Arguments> provideLongArguments(int argumentsCount, int iterationsCount) {
        return Stream.generate(() -> arguments(getArguments(argumentsCount))).limit(iterationsCount);
    }

    private Object[] getArguments(int argumentsCount) {
        return ThreadLocalRandom.current().ints(argumentsCount).boxed().toArray();
    }
}