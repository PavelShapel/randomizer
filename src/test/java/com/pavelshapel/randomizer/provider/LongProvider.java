package com.pavelshapel.randomizer.provider;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@FieldDefaults(
        makeFinal = true,
        level = AccessLevel.PRIVATE
)
@AllArgsConstructor
public abstract class LongProvider implements ArgumentsProvider {
    int argumentsCount;
    int iterationsCount;

    public LongProvider(int argumentsCount) {
        this.argumentsCount = argumentsCount;
        this.iterationsCount = 10;
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