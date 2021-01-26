package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.aop.LogMethodResult;
import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.service.randomizer.collection.map.MapCollectionRandomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.map.MapPrimitiveRandomizer;
import org.apache.commons.lang3.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/map")
public class MapRestController extends AbstractRestController<Map<String, Object>> {
    @LogMethodResult(logLevel = "DEBUG")
    @PostMapping
    public ResponseEntity<RandomEntity<Map<String, Object>>> postMap(@RequestBody Map<String, Object> map) {
        final Map<String, Object> randomEntity = ((MapPrimitiveRandomizer) getPrimitiveRandomizer()).randomize(map);
        return ResponseEntity.ok(getRandomEntity(randomEntity));
    }

    @LogMethodResult(logLevel = "DEBUG")
    @PostMapping(PATH_COLLECTION)
    public ResponseEntity<Collection<RandomEntity<Map<String, Object>>>> postCollection(@RequestBody Map<String, Object> map) {
        final Collection<Map<String, Object>> randomEntities = ((MapCollectionRandomizer) getCollectionRandomizer()).randomize(map);
        final Collection<RandomEntity<Map<String, Object>>> response = randomEntities.stream()
                .map(this::getRandomEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @LogMethodResult(logLevel = "DEBUG")
    @PostMapping(PATH_COLLECTION + PATH_SIZE_RANGE)
    public ResponseEntity<Collection<RandomEntity<Map<String, Object>>>> postCollectionBySize(
            @RequestBody Map<String, Object> map,
            @PathVariable long minSize,
            @PathVariable long maxSize) {
        final Collection<Map<String, Object>> randomEntities = getCollectionRandomizer().randomize(map, Range.between(minSize, maxSize));
        final Collection<RandomEntity<Map<String, Object>>> response = randomEntities.stream()
                .map(this::getRandomEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}