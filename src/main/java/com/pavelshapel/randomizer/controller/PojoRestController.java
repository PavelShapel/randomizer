package com.pavelshapel.randomizer.controller;

import com.pavelshapel.randomizer.aop.LogMethodResult;
import com.pavelshapel.randomizer.service.randomizer.Randomizer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pojo")
public class PojoRestController {
    @Autowired
    ApplicationContext context;

    @LogMethodResult
    @GetMapping
    public ResponseEntity<Map<String, Object>> randomizePojo(@RequestBody Map<String, Object> pojo) {
        final HashMap<String, Object> response = new HashMap<>();
        pojo.forEach((key, value) -> response.put(key, getRandomValueByClassName(value.toString())));

        return ResponseEntity.ok(response);
    }

    private Object getRandomValueByClassName(String className) {
        try {
            final String beanName = Arrays.stream(
                    context.getBeanNamesForType(getResolvableTypeForClassWithGenerics(className))
            ).collect(toSingleton());
            return ((Randomizer<?>) context.getBean(beanName)).randomize();
        } catch (Exception exception) {
            return exception.toString();
        }
    }

    @SneakyThrows
    private ResolvableType getResolvableTypeForClassWithGenerics(String className) {
        final Class<?> classFromName = Class.forName(className);
        return ResolvableType.forClassWithGenerics(Randomizer.class, classFromName);
    }

    private <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }
}