package com.pavelshapel.randomizer;//package com.pavelshapel.randomizer.service;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


class TaskTest {
    @Test
    void test() {

        final List<Student> students = getStudents();

        printDelimiter();
        students.stream()
                .map(student -> String.format("%s %s", student.name, student.marks))
                .forEach(System.out::println);
        printDelimiter();

        Predicate<Student> isGoodMarks = student -> student.marks.stream().noneMatch(mark -> mark <= 7);

        printDelimiter();
        students.stream()
                .filter(isGoodMarks)
                .map(student -> String.format("%s %s", student.name, student.marks))
                .forEach(System.out::println);
        printDelimiter();
    }

    private void printDelimiter() {
        System.out.println("********************************");
    }

    private List<Student> getStudents() {
        return Arrays.asList(
                new Student("pavel1", Arrays.asList(8, 9, 10)),
                new Student("pavel2", Arrays.asList(7, 8, 9, 10)),
                new Student("pavel3", Arrays.asList(9, 10)),
                new Student("pavel4", Arrays.asList(4, 5))
        );
    }

    @AllArgsConstructor
    static class Student {
        String name;
        List<Integer> marks;
    }
}