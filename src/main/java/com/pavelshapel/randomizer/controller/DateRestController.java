package com.pavelshapel.randomizer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/date")
public class DateRestController extends AbstractRestController<Date> {
}