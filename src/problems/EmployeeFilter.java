package problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFilter {
    public static void main(String[] args) {
        List<Employee2> Employee2s = Arrays.asList(new Employee2("John Doe", 500), new Employee2("Jane Smith", 600), new Employee2("Oliver Twist", 700), new Employee2("Nancy Drew", 800), new Employee2("Harry Potter", 900), new Employee2("Hermione Granger", 1000), new Employee2("Ron Weasley", 1100));

        // 1.
        List<Employee2> salaryLess = Employee2s.stream()
                .filter(e -> e.getSalary() < 800)
                .collect(Collectors.toList());
        salaryLess
                .forEach(e -> System.out.println(e.getName()));

        //2.
        List<Employee2> sortedList = Employee2s.stream()
                .sorted(Comparator.comparing(Employee2::getName))
                .collect(Collectors.toList());
        sortedList.
                forEach(e -> System.out.println(e.getName()));

        //3.
        System.out.println(" " + Employee2s.stream()
                .mapToInt(Employee2::getSalary)
                .average()
                .getAsDouble());
    }
}

class Employee2 {
    private String name;
    private int salary;

    public Employee2(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }
}