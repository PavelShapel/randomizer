package com.pavelshapel.randomizer.entity.valuespecification.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EntityValueSpecificationTest {
    @Autowired
    EntityValueSpecification valueSpecification;

    @Test
    void constructor_WithoutParams_ShouldReturnEntity() {


        assertThat(valueSpecification).isNotNull();
    }
}