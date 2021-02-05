package com.pavelshapel.randomizer.controller;

import com.pavelshapel.commonspringbootstarter.utils.randomizer.service.impl.AbstractRangeRandomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/string")
public class StringRestController extends AbstractRangeRestController<String> {
    @Autowired
    public StringRestController(AbstractRangeRandomizer<String> randomizer) {
        super(randomizer);
    }
}