package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.aop.LogMethodResult;
import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.service.randomizer.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public abstract class AbstractRestController<T> {
    public static final String PATH_GENERIC_RANGE = "/{min:[\\d]+}/{max:[\\d]+}";
    public static final String PATH_LIST = "/list";

    @Autowired
    private Randomizer<T> randomizer;

    @LogMethodResult
    @GetMapping
    protected ResponseEntity<RandomEntity<T>> getRandomEntity() {
        return ResponseEntity.ok(randomizer.getRandomEntity());
    }

    @LogMethodResult
    @GetMapping(PATH_GENERIC_RANGE)
    protected ResponseEntity<RandomEntity<T>> getRandomEntity(
            @PathVariable long min,
            @PathVariable long max) {
        return ResponseEntity.ok(randomizer.getRandomEntity(min, max));
    }

    @LogMethodResult
    @GetMapping(PATH_LIST)
    protected ResponseEntity<List<RandomEntity<T>>> getRandomEntityList() {
        return ResponseEntity.ok(randomizer.getRandomEntityList());
    }
}