//package com.pavelshapel.randomizer.controller;
//
//import com.pavelshapel.randomizer.aop.LogMethodResult;
//import com.pavelshapel.randomizer.entity.RandomEntity;
//import com.pavelshapel.randomizer.service.randomizer.array.ArrayRandomizer;
//import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
//import lombok.Getter;
//import org.apache.commons.lang3.Range;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.Arrays;
//
//@Getter
//public abstract class AbstractRestController<T> {
//    public static final String PATH_VALUE_RANGE = "/{minValue:[\\d]+}/{maxValue:[\\d]+}";
//    public static final String PATH_SIZE_RANGE = "/{minSize:[\\d]+}/{maxSize:[\\d]+}";
//    public static final String PATH_ARRAY = "/array";
//
//    @Autowired
//    private PrimitiveRandomizer<T> primitiveRandomizer;
//    @Autowired
//    private ArrayRandomizer<T> arrayRandomizer;
//
//    @LogMethodResult(logLevel = "DEBUG")
//    @GetMapping
//    protected ResponseEntity<RandomEntity<T>> get() {
//        final RandomEntity<T> randomEntity =
//                createRandomEntity(primitiveRandomizer.randomize());
//
//        return ResponseEntity.ok(randomEntity);
//    }
//
//    @LogMethodResult(logLevel = "DEBUG")
//    @GetMapping(PATH_VALUE_RANGE)
//    protected ResponseEntity<RandomEntity<T>> get(
//            @PathVariable long minValue,
//            @PathVariable long maxValue) {
//        final RandomEntity<T> randomEntity =
//                createRandomEntity(primitiveRandomizer.randomize(Range.between(minValue, maxValue)));
//
//        return ResponseEntity.ok(randomEntity);
//    }
//
//    @LogMethodResult(logLevel = "DEBUG")
//    @GetMapping(PATH_ARRAY)
//    protected ResponseEntity<Object[]> getArray() {
//        final Object[] randomEntities = Arrays
//                .stream(arrayRandomizer.randomize())
//                .map(this::createRandomEntity)
//                .toArray();
//
//        return ResponseEntity.ok(randomEntities);
//    }
//
//    @LogMethodResult(logLevel = "DEBUG")
//    @GetMapping(PATH_ARRAY + PATH_SIZE_RANGE)
//    protected ResponseEntity<Object[]> getArrayBySize(
//            @PathVariable long minSize,
//            @PathVariable long maxSize) {
//        final Object[] randomEntities = Arrays
//                .stream(arrayRandomizer.randomize(Range.between(minSize, maxSize)))
//                .map(this::createRandomEntity)
//                .toArray();
//
//        return ResponseEntity.ok(randomEntities);
//    }
//
//    @LogMethodResult(logLevel = "DEBUG")
//    @GetMapping(PATH_VALUE_RANGE + PATH_ARRAY)
//    protected ResponseEntity<Object[]> getArrayByValue(
//            @PathVariable long minValue,
//            @PathVariable long maxValue) {
//        final Object[] randomEntities = Arrays
//                .stream(arrayRandomizer.randomize(Range.between(minValue, maxValue), null))
//                .map(this::createRandomEntity)
//                .toArray();
//
//        return ResponseEntity.ok(randomEntities);
//    }
//
//    @LogMethodResult(logLevel = "DEBUG")
//    @GetMapping(PATH_VALUE_RANGE + PATH_ARRAY + PATH_SIZE_RANGE)
//    protected ResponseEntity<Object[]> getArrayByValueSize(
//            @PathVariable long minValue,
//            @PathVariable long maxValue,
//            @PathVariable long minSize,
//            @PathVariable long maxSize) {
//        final Object[] randomEntities = Arrays
//                .stream(arrayRandomizer.randomize(Range.between(minValue, maxValue), Range.between(minSize, maxSize)))
//                .map(this::createRandomEntity)
//                .toArray();
//
//        return ResponseEntity.ok(randomEntities);
//    }
//
//    protected RandomEntity<T> createRandomEntity(T value) {
//        return new RandomEntity<>(value, primitiveRandomizer.getGenericClass());
//    }
//}