package com.pavelshapel.randomizer.controller;

import com.pavelshapel.aop.spring.boot.starter.log.method.result.LogMethodResult;
import com.pavelshapel.random.spring.boot.starter.randomizer.entity.Entity;
import com.pavelshapel.random.spring.boot.starter.randomizer.entity.Specification;
import com.pavelshapel.random.spring.boot.starter.randomizer.service.collection.CollectionRandomizer;
import com.pavelshapel.random.spring.boot.starter.randomizer.service.factory.RandomizerFactory;
import com.pavelshapel.web.spring.boot.starter.wrapper.TypedResponseWrapperRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@TypedResponseWrapperRestController
@RequestMapping("/")
public class RandomRestController {
    public static final String PATH_TYPE = "/{type:[[a-zA-Z]]+}";
    public static final String PATH_RANGE = "/{min:[\\d]+}/{max:[\\d]+}";

    @Autowired
    private RandomizerFactory genericRandomizerFactory;
    @Autowired
    private CollectionRandomizer genericCollectionRandomizer;

    @LogMethodResult
    @GetMapping(PATH_TYPE)
    public ResponseEntity<Object> randomize(@PathVariable String type) {
        return ResponseEntity.ok(genericRandomizerFactory.getRandomizer(type).randomize());
    }

    @LogMethodResult
    @GetMapping(PATH_TYPE + PATH_RANGE)
    public ResponseEntity<Object> randomize(@PathVariable String type,
                                            @PathVariable long min,
                                            @PathVariable long max) {
        return ResponseEntity.ok(genericRandomizerFactory.getRandomizer(type).randomize(min, max));
    }

    @LogMethodResult
    @PostMapping
    public ResponseEntity<Object> randomize(@RequestBody Specification specification) {
        return ResponseEntity.ok(genericRandomizerFactory.getRandomizer(specification).randomize(specification));
    }

    @LogMethodResult
    @PostMapping("/collection")
    public ResponseEntity<Object> randomize(@RequestBody Collection<Specification> specifications) {
        return ResponseEntity.ok(genericCollectionRandomizer.randomize(specifications));
    }

    @LogMethodResult
    @PostMapping("/entity")
    public ResponseEntity<Object> randomize(@RequestBody Entity entity) {
        return ResponseEntity.ok(genericCollectionRandomizer.randomize(entity));
    }
}