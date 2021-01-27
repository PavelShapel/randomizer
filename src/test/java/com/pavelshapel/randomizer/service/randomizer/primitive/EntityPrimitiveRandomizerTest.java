package com.pavelshapel.randomizer.service.randomizer.primitive;

import com.pavelshapel.randomizer.entity.Entity;
import com.pavelshapel.randomizer.provider.PrimitiveTypeProvider;
import com.pavelshapel.randomizer.service.randomizer.EntityRandomizer;
import com.pavelshapel.randomizer.service.randomizer.Randomizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class EntityPrimitiveRandomizerTest {
    @Autowired
    private EntityPrimitiveRandomizer primitiveRandomizer;
    @Autowired
    private List<Randomizer<?>> randomizers;

    @BeforeEach
    void setUp() {
        randomizers.removeIf(randomizer -> randomizer instanceof EntityRandomizer);
    }

    @Test
    void randomize_WithDefaultRange_ShouldReturnEntity() {
        final Entity randomEntity = primitiveRandomizer.randomize();

        assertThat(randomEntity).isNotNull();
        assertThat(randomEntity.size()).isSameAs(randomizers.size());
    }

    @Test
    void randomize_WithBoundedRange_ShouldReturnEntity() {
        final Entity randomEntity =
                primitiveRandomizer.randomize(DEFAULT_POSITIVE_BYTE_RANGE.getValue());

        assertThat(randomEntity).isNotNull();
        assertThat(randomEntity.size()).isSameAs(randomizers.size());
    }

    @ParameterizedTest
    @ArgumentsSource(PrimitiveTypeProvider.class)
    void randomize_MapAsParam_ShouldReturnEntity(Class<?> targetClass) {
        final Entity defaultEntity = createEntity(targetClass);

        final Entity randomEntity = primitiveRandomizer.randomize(defaultEntity);

        assertThat(randomEntity).isNotNull();
        assertThat(randomEntity.size()).isSameAs(defaultEntity.size());
        assertThat(randomEntity.values().toArray()[0]).isInstanceOf(targetClass);
    }

    private Entity createEntity(Class<?> targetClass) {
        return new Entity("object", targetClass.getSimpleName().toLowerCase());
    }

}