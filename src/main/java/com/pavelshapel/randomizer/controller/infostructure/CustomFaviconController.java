package com.pavelshapel.randomizer.controller.infostructure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomFaviconController {

    @GetMapping("favicon.ico")
    public void returnNoFavicon() {
        //disable "favicon.ico"
    }
}