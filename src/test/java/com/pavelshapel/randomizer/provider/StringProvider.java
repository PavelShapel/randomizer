package com.pavelshapel.randomizer.provider;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@ToString
@EqualsAndHashCode
@FieldDefaults(
        makeFinal = true,
        level = AccessLevel.PRIVATE
)
public abstract class StringProvider implements ArgumentsProvider {
    int argumentsCount;
    int iterationsCount;

    public StringProvider(int argumentsCount) {
        this.argumentsCount = argumentsCount;
        this.iterationsCount = 10;
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return provideStringArguments(argumentsCount, iterationsCount);
    }

    private Stream<Arguments> provideStringArguments(int argumentsCount, int iterationsCount) {
        return Stream.generate(() -> arguments(getArguments(argumentsCount))).limit(iterationsCount);
    }

    private Object[] getArguments(int argumentsCount) {
        return ThreadLocalRandom.current().longs(argumentsCount).mapToObj(String::valueOf).toArray();
    }
}