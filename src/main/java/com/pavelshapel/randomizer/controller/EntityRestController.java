package com.pavelshapel.randomizer.controller;

import com.pavelshapel.commonspringbootstarter.utils.aop.log.methodresult.LogMethodResult;
import com.pavelshapel.commonspringbootstarter.utils.randomizer.entity.Entity;
import com.pavelshapel.commonspringbootstarter.utils.randomizer.service.impl.EntityRandomizer;
import com.pavelshapel.commonspringbootstarter.utils.web.wrapper.typed.TypedResponseWrapperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

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
    public ResponseEntity<Entity> postBoundedValue(@RequestBody Entity entity) {
        final Entity randomValue = getRandomizer().randomizeBoundedValue(entity);

        return ResponseEntity.ok(randomValue);
    }

    @LogMethodResult(logLevel = "DEBUG")
    @PostMapping(PATH_COLLECTION)
    public ResponseEntity<Collection<Entity>> postBoundedValueCollection(@RequestBody Entity entity) {
        final Collection<Entity> randomCollection = getRandomizer().randomizeBoundedValueCollection(entity);

        return ResponseEntity.ok(randomCollection);
    }

    @LogMethodResult(logLevel = "DEBUG")
    @PostMapping(PATH_COLLECTION + PATH_COLLECTION_RANGE)
    public ResponseEntity<Collection<Entity>> postBoundedValueBoundedCollection(
            @RequestBody Entity entity,
            @PathVariable long minCollection,
            @PathVariable long maxCollection) {
        final Collection<Entity> randomCollection = getRandomizer()
                .randomizeBoundedValueBoundedCollection(entity, minCollection, maxCollection);

        return ResponseEntity.ok(randomCollection);
    }
}