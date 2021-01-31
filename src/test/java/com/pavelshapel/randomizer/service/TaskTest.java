package com.pavelshapel.randomizer.service;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

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

    @Test
    void name2() {
        System.out.println();
        System.out.println("Test distinct start");
        Collection<String> ordered = Arrays.asList("a1", "b2", "a2", "b3", "a1", "a2", "a2");
        Collection<String> nonOrdered = new HashSet<>(ordered);

        System.out.println(ordered.stream().collect(Collectors.groupingBy((p) -> p.charAt(0))));

        final String s = ordered.stream().max(String::compareTo).get();
        System.out.println(s);

        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 2);

        System.out.println(integers.stream().mapToInt(value -> value).sum());


        // Получение коллекции без дубликатов
        List<String> distinct = nonOrdered.stream().distinct().collect(Collectors.toList());
        System.out.println("distinct = " + distinct); // напечатает distinct = [a1, a2, a3] - порядок не гарантируется

        List<String> distinctOrdered = ordered.stream().distinct().collect(Collectors.toList());
        System.out.println("distinctOrdered = " + distinctOrdered); // напечатает distinct = [a1, a2, a3] - порядок гарантируется
    }
}
