package core.java.examples.tests;

import core.java.examples.model.Classroom;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestJava8 {

    private static List<String> names = new ArrayList<String>();
    private static List<Classroom> classrooms = new ArrayList<Classroom>();

    public static void main(String[] args) {

        initialize();

        //names list methods
        simpleStreamForeachLoop();
        simpleParallelStreamForeachLoop();
        modeFiveNames();

        //classrooms list methods
        countStudents();
        countCourses();
        isThereACourse5();
        concatenateCourses();
        groupByCourses();
    }

    private static void groupByCourses() {
        Map<String, Long> courseCountMap = classrooms.stream()
                .findFirst().get()
                .getStudents()
                .stream()
                .flatMap(x -> x.getCourses().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("Courses count: " + courseCountMap);
    }

    private static void concatenateCourses() {
        //get the first classroom, for all its students get distinct
        // courses and at last, concat courses by comma
        BinaryOperator<String> accumulator1 = (s1, s2) -> s1 + "," + s2;
        Stream<String> distinctCourses = classrooms.stream()
                .findFirst().get()
                .getStudents()
                .stream()
                .flatMap(x -> x.getCourses().stream())
                .distinct();
        String courses = distinctCourses
                .reduce(accumulator1)
                .get();

        System.out.println("Distinct concatenated courses: " + courses);
    }

    private static void isThereACourse5() {
        Boolean courseFiveMatch = classrooms.stream().flatMap(e -> e.getStudents().stream()).flatMap(x -> x.getCourses().stream()).anyMatch(x -> x.contains("Course 5"));
        Assert.assertEquals(true, courseFiveMatch);
    }

    private static void countCourses() {
        Long countCourses = classrooms.stream().flatMap(e -> e.getStudents().stream()).flatMap(x -> x.getCourses().stream()).count();
        System.out.println("Number of courses: " + countCourses);
    }

    private static void countStudents() {
        System.out.println("Number of students: " + classrooms.stream().flatMap(e -> e.getStudents().stream()).count());
    }

    private static void modeFiveNames() {
        System.out.println("modeFiveNames:");
        Predicate<String> modeFivePredicate = e -> Integer.valueOf(e.split(" ")[1].toString()) % 5 == 0;
        names.parallelStream().filter(modeFivePredicate).forEach(System.out::println);
    }

    private static void simpleParallelStreamForeachLoop() {
        System.out.println("simpleParallelStreamForeachLoop:");
        names.parallelStream().forEach(System.out::println);
    }

    private static void simpleStreamForeachLoop() {
        System.out.println("simpleStreamForeachLoop:");
        names.stream().forEach(System.out::println);
    }

    private static void initialize() {
        Stream.iterate(1, x -> x + 1).limit(40).forEach(e -> names.add("Hamed " + e));

        Consumer<Integer> newClassroomConsumer = e -> classrooms.add(new Classroom(e, "Class " + e));
        Stream.iterate(1, x -> x + 1).limit(20).forEach(newClassroomConsumer);
    }
}
