package com.pavelshapel.randomizer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/double")
public class DoubleRestController extends AbstractRestController<Double> {
}