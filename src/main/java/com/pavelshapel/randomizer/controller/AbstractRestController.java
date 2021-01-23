package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.aop.LogMethodResult;
import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.service.randomizer.randomentity.RandomEntityRandomizer;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

public abstract class AbstractRestController<T> {
    public static final String PATH_GENERIC_RANGE = "/{min:[\\d]+}/{max:[\\d]+}";
    public static final String PATH_COLLECTION = "/collection";

    @Autowired
    private RandomEntityRandomizer<T> randomEntityRandomizer;

    @LogMethodResult
    @GetMapping
    protected ResponseEntity<RandomEntity<T>> getRandomEntity() {
        return ResponseEntity.ok(randomEntityRandomizer.randomize());
    }

    @LogMethodResult
    @GetMapping(PATH_GENERIC_RANGE)
    protected ResponseEntity<RandomEntity<T>> getRandomEntity(
            @PathVariable long min,
            @PathVariable long max) {
        return ResponseEntity.ok(randomEntityRandomizer.randomize(Range.between(min, max)));
    }

    @LogMethodResult
    @GetMapping(PATH_COLLECTION)
    protected ResponseEntity<Collection<RandomEntity<T>>> getRandomEntityCollection() {
        return ResponseEntity.ok(randomEntityRandomizer.randomizeCollection());
    }
}