package com.pavelshapel.randomizer.controller;

import com.pavelshapel.commonspringbootstarter.utils.aop.log.methodresult.LogMethodResult;
import com.pavelshapel.commonspringbootstarter.utils.randomizer.entity.Entity;
import com.pavelshapel.commonspringbootstarter.utils.randomizer.entity.specification.Specification;
import com.pavelshapel.commonspringbootstarter.utils.randomizer.service.collection.impl.GenericCollectionRandomizer;
import com.pavelshapel.commonspringbootstarter.utils.randomizer.service.factory.impl.GenericRandomizerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
//@TypedResponseWrapperController
public class RandomRestController {
    public static final String PATH_TYPE = "/{type:[[a-zA-Z]]+}";
    public static final String PATH_RANGE = "/{min:[\\d]+}/{max:[\\d]+}";

    @Autowired
    private GenericRandomizerFactory genericRandomizerFactory;
    @Autowired
    private GenericCollectionRandomizer genericCollectionRandomizer;

    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping(PATH_TYPE)
    public ResponseEntity<Object> randomize(@PathVariable String type) {
        return ResponseEntity.ok(genericRandomizerFactory.getRandomizer(type).randomize());
    }

    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping(PATH_TYPE + PATH_RANGE)
    public ResponseEntity<Object> randomize(@PathVariable String type,
                                            @PathVariable long min,
                                            @PathVariable long max) {
        return ResponseEntity.ok(genericRandomizerFactory.getRandomizer(type).randomize(min, max));
    }

    @LogMethodResult(logLevel = "DEBUG")
    @PostMapping
    public ResponseEntity<Object> randomize(@RequestBody Specification specification) {
        return ResponseEntity.ok(genericRandomizerFactory.getRandomizer(specification).randomize(specification));
    }

    @LogMethodResult(logLevel = "DEBUG")
    @PostMapping("/collection")
    public ResponseEntity<Object> randomize(@RequestBody Collection<? extends Specification> specifications) {
        return ResponseEntity.ok(genericCollectionRandomizer.randomize(specifications));
    }

    @LogMethodResult(logLevel = "DEBUG")
    @PostMapping("/entity")
    public ResponseEntity<Object> randomize(@RequestBody Entity entity) {
        return ResponseEntity.ok(genericCollectionRandomizer.randomize(entity));
    }
}