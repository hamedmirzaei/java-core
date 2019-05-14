package core.java.examples.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Student {
    private Integer id;
    private String name;
    private List<String> courses;

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<String>();
        Consumer<Integer> newCourseConsumer = e -> this.courses.add("Course " + e);
        Stream.iterate(1, x -> x+1).limit(5).forEach(newCourseConsumer);
    }
}
