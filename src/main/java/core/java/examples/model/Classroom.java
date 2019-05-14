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
public class Classroom {

    private Integer id;
    private String name;
    private List<Student> students;

    public Classroom(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.students = new ArrayList<Student>();
        Consumer<Integer> newStudentConsumer = e -> this.students.add(new Student(e,"core.java.examples.model.Student " + e));
        Stream.iterate(1, x -> x+1).limit(10).forEach(newStudentConsumer);
    }

}
