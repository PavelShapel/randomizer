package com.pavelshapel.randomizer.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class NumberSpecificationTest {

    @Test
    void constructor_WithoutParams_ShouldReturnDefaultPojo() {
        final NumberSpecification numberSpecification = new NumberSpecification();

        assertThat(numberSpecification.getMin()).isEqualTo(NumberSpecification.DEFAULT_MIN);
        assertThat(numberSpecification.getMax()).isEqualTo(NumberSpecification.DEFAULT_MAX);
        assertThat(numberSpecification.getPrecision()).isEqualTo(NumberSpecification.DEFAULT_PRECISION);
    }

    @ParameterizedTest
    @MethodSource("provideValidArguments")
    void constructor_WithValidMinMaxParams_ShouldReturnPojo(int min, int max, int precision) {
        final NumberSpecification numberSpecification = new NumberSpecification(min, max);

        assertThat(numberSpecification.getMin()).isEqualTo(Math.min(min, max));
        assertThat(numberSpecification.getMax()).isEqualTo(Math.max(min, max));
        assertThat(numberSpecification.getPrecision()).isEqualTo(NumberSpecification.DEFAULT_PRECISION);
    }

    @ParameterizedTest
    @MethodSource("provideValidArguments")
    void constructor_WithValidAllParams_ShouldReturnPojo(int min, int max, int precision) {
        final NumberSpecification numberSpecification = new NumberSpecification(min, max, precision);

        assertThat(numberSpecification.getMin()).isEqualTo(Math.min(min, max));
        assertThat(numberSpecification.getMax()).isEqualTo(Math.max(min, max));
        assertThat(numberSpecification.getPrecision()).isEqualTo(precision);
    }

    private static Stream<Arguments> provideValidArguments() {
        return Stream.of(
                arguments(-10, 100, -1),
                arguments(999, 55, 2),
                arguments(12, 44, 4)
        );
    }
}