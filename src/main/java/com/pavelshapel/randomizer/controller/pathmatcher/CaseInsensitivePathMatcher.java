package com.pavelshapel.randomizer.controller.pathmatcher;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Map;
import java.util.Objects;

@Component
public class CaseInsensitivePathMatcher extends AntPathMatcher {
    @Override
    protected boolean doMatch(
            String pattern,
            String path,
            boolean fullMatch,
            Map<String, String> uriTemplateVariables) {
        return super.doMatch(
                pattern.toLowerCase(),
                Objects.requireNonNull(path).toLowerCase(),
                fullMatch,
                uriTemplateVariables
        );
    }
}