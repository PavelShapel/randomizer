package com.pavelshapel.randomizer.controller;

import com.pavelshapel.commonspringbootstarter.utils.randomizer.service.AbstractRandomizer;
import com.pavelshapel.randomizer.aop.LogMethodResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Getter
@AllArgsConstructor
public abstract class AbstractRestController<T, O> {
    public static final String PATH_VALUE_RANGE = "/{minValue:[\\d]+}/{maxValue:[\\d]+}";
    public static final String PATH_COLLECTION_RANGE = "/{minCollection:[\\d]+}/{maxCollection:[\\d]+}";
    public static final String PATH_COLLECTION = "/collection";

    private final AbstractRandomizer<T, O> randomizer;

    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping
    protected ResponseEntity<T> getValue() {
        final T randomValue = randomizer.randomizeValue();

        return ResponseEntity.ok(randomValue);
    }

    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping(PATH_COLLECTION)
    protected ResponseEntity<Collection<T>> getCollection() {
        final Collection<T> randomCollection = randomizer.randomizeCollection();

        return ResponseEntity.ok(randomCollection);
    }

    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping(PATH_COLLECTION + PATH_COLLECTION_RANGE)
    protected ResponseEntity<Collection<T>> getBoundedCollection(
            @PathVariable long minCollection,
            @PathVariable long maxCollection) {
        final Collection<T> randomCollection = randomizer.randomizeBoundedCollection(minCollection, maxCollection);

        return ResponseEntity.ok(randomCollection);
    }
}