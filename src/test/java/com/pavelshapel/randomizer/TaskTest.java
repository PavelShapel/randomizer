package com.pavelshapel.randomizer;//package com.pavelshapel.randomizer.service;

import com.pavelshapel.randomizer.service.randomizer.array.LongArrayRandomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.LongPrimitiveRandomizer;
import com.pavelshapel.randomizer.service.randomizer.primitive.StringPrimitiveRandomizer;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@SpringBootTest
@ContextConfiguration(classes = {
        StringPrimitiveRandomizer.class,
        LongArrayRandomizer.class,
        LongPrimitiveRandomizer.class
})
class TaskTest {
    @Autowired
    StringPrimitiveRandomizer stringPrimitiveRandomizer;
    @Autowired
    LongArrayRandomizer longArrayRandomizer;

    @Test
    void test() {

        //final List<Student> students = getStudents();
        final List<Student> students = getRandomStudents();

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

    private List<Student> getRandomStudents() {
        final ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            final String randomName = stringPrimitiveRandomizer.randomize(Range.between(3L, 10L));
            final Long[] randomMark = longArrayRandomizer.randomize(Range.between(6L, 10L), Range.between(1L, 5L));

            students.add(new Student(randomName, Arrays.asList(randomMark.clone())));
        }
        return students;
    }

    private List<Student> getStudents() {
        return Arrays.asList(
                new Student("pavel1", Arrays.asList(8L, 9L, 10L)),
                new Student("pavel2", Arrays.asList(7L, 8L, 9L, 10L)),
                new Student("pavel3", Arrays.asList(9L, 10L)),
                new Student("pavel4", Arrays.asList(4L, 5L))
        );
    }

    @AllArgsConstructor
    static class Student {
        String name;
        List<Long> marks;
    }
}