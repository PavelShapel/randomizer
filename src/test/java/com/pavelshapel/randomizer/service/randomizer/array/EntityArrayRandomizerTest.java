//package com.pavelshapel.randomizer.service.randomizer.array;
//
//import com.pavelshapel.randomizer.entity.Entity;
//import com.pavelshapel.randomizer.provider.FourParametersLongProvider;
//import com.pavelshapel.randomizer.provider.PrimitiveTypeProvider;
//import com.pavelshapel.randomizer.provider.TwoParametersLongProvider;
//import com.pavelshapel.randomizer.service.randomizer.EntityRandomizer;
//import com.pavelshapel.randomizer.service.randomizer.Randomizer;
//import org.apache.commons.lang3.Range;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static com.pavelshapel.randomizer.entity.DefaultRanges.DEFAULT_POSITIVE_BYTE_RANGE;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//class EntityArrayRandomizerTest {
//    @Autowired
//    private EntityArrayRandomizer arrayRandomizer;
//    @Autowired
//    private List<Randomizer<?>> randomizers;
//
//    @BeforeEach
//    void setUp() {
//        randomizers.removeIf(randomizer -> randomizer instanceof EntityRandomizer);
//    }
//
//    @Test
//    void randomize_WithoutParams_ShouldReturnArray() {
//        final Entity[] randomArray = arrayRandomizer.randomize();
//
//        final long arrayLength = Arrays.stream(randomArray)
//                .peek(value -> assertThat(value).isInstanceOf(Entity.class))
//                .peek(value -> assertThat(value.size()).isSameAs(randomizers.size()))
//                .count();
//        assertThat(arrayLength).isBetween(
//                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
//                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
//        );
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(TwoParametersLongProvider.class)
//    void randomize_RangeAsParam_ShouldReturnArray(long min, long max) {
//        final Range<Long> range = Range.between(min, max);
//
//        final Entity[] randomArray = arrayRandomizer.randomize(range);
//
//        final long arrayLength = Arrays.stream(randomArray)
//                .peek(value -> assertThat(value).isInstanceOf(Entity.class))
//                .peek(value -> assertThat(value.size()).isSameAs(randomizers.size()))
//                .count();
//        assertThat(arrayLength).isBetween(
//                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
//                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
//        );
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(FourParametersLongProvider.class)
//    void randomize_WithValueSizeRange_ShouldReturnArray(long minValue, long maxValue, long minSize, long maxSize) {
//        final Range<Long> rangeValue = Range.between(minValue, maxValue);
//        final Range<Long> rangeSize = Range.between(minSize, maxSize);
//
//        final Entity[] randomArray = arrayRandomizer.randomize(rangeValue, rangeSize);
//
//        final long arrayLength = Arrays.stream(randomArray)
//                .peek(value -> assertThat(value).isInstanceOf(Entity.class))
//                .peek(value -> assertThat(value.size()).isSameAs(randomizers.size()))
//                .count();
//        assertThat(arrayLength).isBetween(
//                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
//                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
//        );
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(PrimitiveTypeProvider.class)
//    void randomize_EntityAsParam_ShouldReturnArray(Class<?> targetClass) {
//        final Entity randomEntity = createEntity(targetClass);
//
//        final Entity[] randomArray = arrayRandomizer.randomize(randomEntity);
//
//        final long arrayLength = Arrays.stream(randomArray)
//                .peek(value -> assertThat(value).isInstanceOf(Entity.class))
//                .peek(value -> assertThat(value).hasSameSizeAs(randomEntity))
//                .peek(value -> assertThat(value.get("object")).isInstanceOf(targetClass))
//                .count();
//        assertThat(arrayLength).isBetween(
//                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMinimum(),
//                DEFAULT_POSITIVE_BYTE_RANGE.getValue().getMaximum()
//        );
//    }
//
//    private Entity createEntity(Class<?> targetClass) {
//        return new Entity("object", targetClass.getSimpleName().toLowerCase());
//    }
//}