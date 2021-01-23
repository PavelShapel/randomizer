package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.aop.LogMethodResult;
import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.service.Utilities;
import com.pavelshapel.randomizer.service.randomizer.collection.CollectionRandomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.PrimitiveRandomizer;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class AbstractRestController<T> {
    public static final String PATH_GENERIC_RANGE = "/{min:[\\d]+}/{max:[\\d]+}";
    public static final String PATH_COLLECTION = "/collection";

    @Autowired
    private PrimitiveRandomizer<T> primitiveRandomizer;
    @Autowired
    private CollectionRandomizer<T> collectionRandomizer;
    @Autowired
    private Utilities utilities;

    @LogMethodResult
    @GetMapping
    protected ResponseEntity<RandomEntity<T>> getRandomEntity() {
        final RandomEntity<T> randomEntity = getRandomEntity(primitiveRandomizer.randomize());
        return ResponseEntity.ok(randomEntity);
    }

    @LogMethodResult
    @GetMapping(PATH_GENERIC_RANGE)
    protected ResponseEntity<RandomEntity<T>> getRandomEntity(
            @PathVariable long min,
            @PathVariable long max) {
        final RandomEntity<T> randomEntity = getRandomEntity(primitiveRandomizer.randomize(Range.between(min, max)));
        return ResponseEntity.ok(randomEntity);
    }

    @LogMethodResult
    @GetMapping(PATH_COLLECTION)
    protected ResponseEntity<Collection<RandomEntity<T>>> getRandomEntityCollection() {
        final Collection<RandomEntity<T>> randomEntities = collectionRandomizer.randomize().stream()
                .map(this::getRandomEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(randomEntities);
    }

    @LogMethodResult
    @GetMapping(PATH_COLLECTION + PATH_GENERIC_RANGE)
    protected ResponseEntity<Collection<RandomEntity<T>>> getRandomEntityCollection(
            @PathVariable long min,
            @PathVariable long max) {
        final Collection<RandomEntity<T>> randomEntities = collectionRandomizer.randomize(Range.between(min, max)).stream()
                .map(this::getRandomEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(randomEntities);
    }

    private RandomEntity<T> getRandomEntity(T value) {
        return new RandomEntity<>(value, getGenericParameterClass());
    }

    private Class<T> getGenericParameterClass() {
        return (Class<T>) utilities.getSuperClassGenericType(primitiveRandomizer, 0);
    }
}