package com.pavelshapel.randomizer.controller;

import com.pavelshapel.commonspringbootstarter.utils.randomizer.service.impl.AbstractRangeRandomizer;
import com.pavelshapel.commonspringbootstarter.utils.web.wrapper.typed.TypedResponseWrapperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@TypedResponseWrapperController
@RequestMapping("/date")
public class DateRestController extends AbstractRangeRestController<Date> {
    @Autowired
    public DateRestController(AbstractRangeRandomizer<Date> randomizer) {
        super(randomizer);
    }
}