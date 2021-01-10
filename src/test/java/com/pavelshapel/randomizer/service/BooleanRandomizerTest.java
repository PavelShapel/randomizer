package com.pavelshapel.randomizer.service;

import com.pavelshapel.randomizer.service.randomizer.BooleanRandomizer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = BooleanRandomizer.class)
class BooleanRandomizerTest {
    @Autowired
    BooleanRandomizer booleanRandomizer;

    @Test
    void randomize_WithDefaultRange_ShouldReturnBoolean() {
        final Boolean randomBoolean = booleanRandomizer.randomize();

        assertThat(randomBoolean).isIn(false, true);
    }
}