package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.provider.OneParametersLongProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = LongRestController.class)
class LongRestControllerTest extends AbstractRestControllerTest<Long> {
    @ParameterizedTest
    @ArgumentsSource(OneParametersLongProvider.class)
    void get_WithoutParams_ShouldReturnRandomEntity(Long value) {
        get(value);
    }

    @ParameterizedTest
    @ArgumentsSource(OneParametersLongProvider.class)
    void get_RangeAsParam_ShouldReturnRandomEntity(Long value) {
        getByRange(value);
    }

    @Override
    protected RandomEntity<Long> createMockRandomEntity(Long value) {
        return new RandomEntity<>(value, Long.class);
    }
}