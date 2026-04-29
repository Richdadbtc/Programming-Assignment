import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents an Employee in the company.
 */
class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return String.format("Employee{name='%s', age=%d, dept='%s', salary=%.2f}", 
                             name, age, department, salary);
    }
}

/**
 * Main program to demonstrate the usage of Function interface and Java Streams.
 */
public class EmployeeDataProcessor {

    public static void main(String[] args) {
        // 1. Read the dataset and store it in a collection
        List<Employee> employees = getSampleEmployeeData();
        System.out.println("--- Original Employee Dataset ---");
        employees.forEach(System.out::println);

        // 2. Write a function using the Function interface
        // Takes an employee object as input and returns concatenated name and department
        Function<Employee, String> nameAndDeptFunc = emp -> emp.getName() + " (" + emp.getDepartment() + ")";

        // 3. Using streams to generate a new collection of concatenated strings
        List<String> employeeDescriptions = employees.stream()
                .map(nameAndDeptFunc)
                .collect(Collectors.toList());

        System.out.println("\n--- Employee Name and Department Descriptions ---");
        employeeDescriptions.forEach(System.out::println);

        // 4. Find the average salary of all employees using stream's built-in functions
        OptionalDouble averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average();

        if (averageSalary.isPresent()) {
            System.out.printf("\nAverage Salary of All Employees: $%.2f%n", averageSalary.getAsDouble());
        }

        // 5. Generalize program by incorporating a filter function for age > 30
        int ageThreshold = 30;
        List<Employee> seniorEmployees = employees.stream()
                .filter(emp -> emp.getAge() > ageThreshold)
                .collect(Collectors.toList());

        System.out.println("\n--- Employees Older Than " + ageThreshold + " ---");
        seniorEmployees.forEach(System.out::println);

        // Bonus: Chaining operations - Names of senior employees with salary > 60000
        List<String> highPaidSeniors = employees.stream()
                .filter(emp -> emp.getAge() > 30)
                .filter(emp -> emp.getSalary() > 60000)
                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println("\n--- High-Paid Senior Employees (Bonus Feature) ---");
        highPaidSeniors.forEach(System.out::println);
    }

    /**
     * Helper method to generate sample dataset.
     */
    private static List<Employee> getSampleEmployeeData() {
        List<Employee> dataset = new ArrayList<>();
        dataset.add(new Employee("Alice Smith", 28, "Engineering", 75000));
        dataset.add(new Employee("Bob Johnson", 35, "Marketing", 55000));
        dataset.add(new Employee("Charlie Brown", 42, "HR", 60000));
        dataset.add(new Employee("Diana Prince", 25, "Engineering", 82000));
        dataset.add(new Employee("Ethan Hunt", 31, "Security", 90000));
        dataset.add(new Employee("Fiona Gallagher", 29, "Operations", 48000));
        return dataset;
    }
}
