package com.pavelshapel.randomizer.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TaskTest {
    @Test
    void name() {
        final List<String> strings = Arrays.asList(
                "word1", "word1", "#hash1", "word1", "#hash2", "word1", "#hash3", "word1",
                "word1", "#hash1", "word1", "#hash7", "word13", "#hash5", "word1", "word1",
                "word1", "#hash4", "word1", "#hash2", "word1", "#hash7", "word1", "home7898", "#hash11", "#hashabc");

        Arrays.stream(getHashes(strings)).forEach(System.out::println);

    }

    public String[] getHashes(List<String> sources) {
        return sources.stream()
                .filter(s -> s.matches("^#hash\\d+$"))
                .distinct()
                .sorted(Comparator.comparing(o -> Integer.valueOf(o.replaceAll("#hash", ""))))
                .toArray(String[]::new);
    }
}
