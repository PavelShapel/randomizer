package com.pavelshapel.randomizer.controller;

import com.pavelshapel.commonspringbootstarter.utils.randomizer.service.impl.AbstractRangeRandomizer;
import com.pavelshapel.commonspringbootstarter.utils.web.wrapper.typed.TypedResponseWrapperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@TypedResponseWrapperController
@RequestMapping("/double")
public class DoubleRestController extends AbstractRangeRestController<Double> {
    @Autowired
    public DoubleRestController(AbstractRangeRandomizer<Double> randomizer) {
        super(randomizer);
    }
}