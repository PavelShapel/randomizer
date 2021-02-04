package com.pavelshapel.randomizer.controller.infostructure;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public RedirectView handleError() {
        return new RedirectView("/actuator/mappings");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}