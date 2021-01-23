package com.pavelshapel.randomizer.service.randomizer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PojoRandomizerTest {
    private final PojoRandomizer pojoRandomizer;

    @Autowired
    public PojoRandomizerTest(PojoRandomizer pojoRandomizer) {
        this.pojoRandomizer = pojoRandomizer;
    }

    @Test
    void randomize_Default_ShouldReturnPojo() {
        final Map<String, Object> randomMap = pojoRandomizer.randomize();

        assertThat(randomMap).isNotNull();
    }
}