package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.aop.LogMethodResult;
import com.pavelshapel.randomizer.entity.RandomEntity;
import com.pavelshapel.randomizer.service.randomizer.primitive.map.MapRandomizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/map")
public class MapRestController extends AbstractRestController<Map<String, Object>> {
    @LogMethodResult(logLevel = "DEBUG")
    @GetMapping("/init")
    public ResponseEntity<RandomEntity<Map<String, Object>>> getMap(@RequestBody Map<String, Object> map) {
        final Map<String, Object> randomMap = ((MapRandomizer<Map<String, Object>>) getPrimitiveRandomizer()).randomize(map);
        return ResponseEntity.ok(getRandomEntity(randomMap));
    }
}