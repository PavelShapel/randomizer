package com.pavelshapel.randomizer.controller.pathmatcher;

import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected void configurePathMatch(PathMatchConfigurer configure) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configure.setPathMatcher(matcher);
    }

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        DateFormatterRegistrar registrar = new DateFormatterRegistrar();
        registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
        registrar.registerFormatters(registry);
    }
}