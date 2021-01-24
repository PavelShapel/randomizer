package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.aop.LogMethodResult;
import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.service.Utilities;
import com.pavelshapel.randomizer.service.randomizer.collection.CollectionRandomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import lombok.Getter;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public abstract class AbstractRestController<T> {
    public static final String PATH_VALUE_RANGE = "/{minValue:[\\d]+}/{maxValue:[\\d]+}";
    public static final String PATH_SIZE_RANGE = "/{minSize:[\\d]+}/{maxSize:[\\d]+}";
    public static final String PATH_COLLECTION = "/collection";

    @Autowired
    private PrimitiveRandomizer<T> primitiveRandomizer;
    @Autowired
    private CollectionRandomizer<T> collectionRandomizer;
    @Autowired
    private Utilities utilities;

    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping
    protected ResponseEntity<RandomEntity<T>> getRandomEntity() {
        final RandomEntity<T> randomEntity = getRandomEntity(primitiveRandomizer.randomize());
        return ResponseEntity.ok(randomEntity);
    }

    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping(PATH_VALUE_RANGE)
    protected ResponseEntity<RandomEntity<T>> get(
            @PathVariable long minValue,
            @PathVariable long maxValue) {
        final RandomEntity<T> randomEntity = getRandomEntity(primitiveRandomizer.randomize(Range.between(minValue, maxValue)));
        return ResponseEntity.ok(randomEntity);
    }

    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping(PATH_COLLECTION)
    protected ResponseEntity<Collection<RandomEntity<T>>> getCollection() {
        final Collection<RandomEntity<T>> randomEntities = collectionRandomizer.randomize().stream()
                .map(this::getRandomEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(randomEntities);
    }

    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping(PATH_COLLECTION + PATH_SIZE_RANGE)
    protected ResponseEntity<Collection<RandomEntity<T>>> getCollectionBySize(
            @PathVariable long minSize,
            @PathVariable long maxSize) {
        final Collection<RandomEntity<T>> randomEntities = collectionRandomizer.randomize(Range.between(minSize, maxSize)).stream()
                .map(this::getRandomEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(randomEntities);
    }

    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping(PATH_VALUE_RANGE + PATH_COLLECTION)
    protected ResponseEntity<Collection<RandomEntity<T>>> getCollectionByValue(
            @PathVariable long minValue,
            @PathVariable long maxValue) {
        final Collection<RandomEntity<T>> randomEntities = collectionRandomizer.randomize(Range.between(minValue, maxValue), null).stream()
                .map(this::getRandomEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(randomEntities);
    }

    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping(PATH_VALUE_RANGE + PATH_COLLECTION + PATH_SIZE_RANGE)
    protected ResponseEntity<Collection<RandomEntity<T>>> getCollectionByValueSize(
            @PathVariable long minValue,
            @PathVariable long maxValue,
            @PathVariable long minSize,
            @PathVariable long maxSize) {
        final Collection<RandomEntity<T>> randomEntities = collectionRandomizer
                .randomize(Range.between(minValue, maxValue), Range.between(minSize, maxSize)).stream()
                .map(this::getRandomEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(randomEntities);
    }

    protected RandomEntity<T> getRandomEntity(T value) {
        return new RandomEntity<>(value, getGenericParameterClass());
    }

    private Class<T> getGenericParameterClass() {
        final Class<?> superClassGenericType = utilities.getSuperClassGenericType(primitiveRandomizer, 0);
        return (Class<T>) superClassGenericType;
    }
}