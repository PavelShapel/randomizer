package com.pavelshapel.randomizer.controller;

import com.pavelshapel.commonspringbootstarter.utils.randomizer.entity.Entity;
import com.pavelshapel.commonspringbootstarter.utils.randomizer.service.impl.EntityRandomizer;
import com.pavelshapel.commonspringbootstarter.utils.web.wrapper.typed.TypedResponseWrapperController;
import com.pavelshapel.randomizer.aop.LogMethodResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@TypedResponseWrapperController
@RequestMapping("/entity")
public class EntityRestController extends AbstractRestController<Entity, Entity> {
    @Autowired
    public EntityRestController(EntityRandomizer randomizer) {
        super(randomizer);
    }

    @Override
    public EntityRandomizer getRandomizer() {
        return ((EntityRandomizer) super.getRandomizer());
    }

    @LogMethodResult(logLevel = "DEBUG")
    @PostMapping
    public ResponseEntity<Entity> postEntity(@RequestBody Entity entity) {
        final Entity randomEntity = getRandomizer().randomizeBoundedValue(entity);

        return ResponseEntity.ok(randomEntity);
    }
//
//    @LogMethodResult(logLevel = "DEBUG")
//    @PostMapping(PATH_ARRAY)
//    public ResponseEntity<Object[]> postArray(@RequestBody Entity entity) {
//        final Entity[] randomEntities = ((EntityArrayRandomizer) getArrayRandomizer()).randomize(entity);
//        final Object[] response = Arrays.stream(randomEntities)
//                .map(this::createRandomEntity)
//                .toArray();
//
//        return ResponseEntity.ok(response);
//    }
//
//    @LogMethodResult(logLevel = "DEBUG")
//    @PostMapping(PATH_ARRAY + PATH_SIZE_RANGE)
//    public ResponseEntity<Object[]> postArrayBySize(
//            @RequestBody Entity entity,
//            @PathVariable long minSize,
//            @PathVariable long maxSize) {
//        final Entity[] randomEntities = getArrayRandomizer().randomize(entity, Range.between(minSize, maxSize));
//        final Object[] response = Arrays.stream(randomEntities)
//                .map(this::createRandomEntity)
//                .toArray();
//
//        return ResponseEntity.ok(response);
//    }
}