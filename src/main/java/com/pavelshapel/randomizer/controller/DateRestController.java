package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.aop.LogMethodResult;
import com.pavelshapel.randomizer.entity.RandomEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/")
public class DateRestController extends AbstractRestController<Date> {
    public static final String PATH = "/date";

    @LogMethodResult
    @GetMapping(PATH)
    @Override
    protected ResponseEntity<RandomEntity<Date>> randomize() {
        return ResponseEntity.ok(getRandomEntity());
    }

    @LogMethodResult
    @GetMapping(PATH + PATH_GENERIC_RANGE)
    @Override
    protected ResponseEntity<RandomEntity<Date>> randomize(
            @PathVariable long min,
            @PathVariable long max) {
        return ResponseEntity.ok(getRandomEntity(min, max));
    }
}