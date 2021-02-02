package com.pavelshapel.randomizer.entity.valuespecification.entity;

import com.pavelshapel.randomizer.entity.specification.value.entity.EntitySpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class EntityValueSpecificationTest {
    @Autowired
    EntitySpecification valueSpecification;

    @Test
    void initialize_ShouldReturnNotNull() {
        assertThat(valueSpecification).isNotNull();
    }
}