package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.aop.LogMethodResult;
import com.pavelshapel.randomizer.service.randomizer.PojoRandomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/pojo")
public class PojoRestController {
    private final PojoRandomizer pojoRandomizer;

    @Autowired
    public PojoRestController(PojoRandomizer pojoRandomizer) {
        this.pojoRandomizer = pojoRandomizer;
    }

    @LogMethodResult
    @GetMapping
    public ResponseEntity<Map<String, Object>> randomize(@RequestBody Map<String, Object> pojo) {
        return ResponseEntity.ok(pojoRandomizer.randomize(pojo));
    }

    @LogMethodResult
    @GetMapping(AbstractRestController.PATH_COLLECTION)
    public ResponseEntity<Collection<Map<String, Object>>> randomizeCollection() {
        return ResponseEntity.ok(pojoRandomizer.randomizeCollection());
    }
}