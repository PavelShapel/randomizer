//package com.pavelshapel.randomizer.controller;
//
//import com.pavelshapel.randomizer.aop.LogMethodResult;
//import com.pavelshapel.randomizer.entity.Entity;
//import com.pavelshapel.randomizer.entity.RandomEntity;
//import com.pavelshapel.randomizer.service.randomizer.array.EntityArrayRandomizer;
//import com.pavelshapel.randomizer.service.randomizer.primitive.EntityPrimitiveRandomizer;
//import org.apache.commons.lang3.Range;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Arrays;
//
//@RestController
//@RequestMapping("/entity")
//public class EntityRestController extends AbstractRestController<Entity> {
//    @LogMethodResult(logLevel = "DEBUG")
//    @PostMapping
//    public ResponseEntity<RandomEntity<Entity>> postEntity(@RequestBody Entity entity) {
//        final Entity randomEntity = ((EntityPrimitiveRandomizer) getPrimitiveRandomizer()).randomize(entity);
//
//        return ResponseEntity.ok(createRandomEntity(randomEntity));
//    }
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
//}