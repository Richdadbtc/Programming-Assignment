import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a Student in the university system.
 */
class Student {
    private String name;
    private String id;
    private List<Course> enrolledCourses;
    private Map<Course, Double> grades;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public List<Course> getEnrolledCourses() { return enrolledCourses; }

    /**
     * Enrolls the student in a course.
     */
    public void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            course.incrementEnrolledCount();
        }
    }

    /**
     * Assigns a grade to the student for a specific course.
     */
    public void assignGrade(Course course, double grade) {
        if (enrolledCourses.contains(course)) {
            grades.put(course, grade);
        }
    }

    public Double getGradeForCourse(Course course) {
        return grades.get(course);
    }

    public Map<Course, Double> getGrades() {
        return grades;
    }
}

/**
 * Represents a Course in the university system.
 */
class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int currentEnrollment;

    // Static variable to track total enrolled students across all courses
    private static int totalEnrolledStudentsAcrossAllCourses = 0;

    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.currentEnrollment = 0;
    }

    // Getters
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getMaxCapacity() { return maxCapacity; }
    public int getCurrentEnrollment() { return currentEnrollment; }

    public void incrementEnrolledCount() {
        this.currentEnrollment++;
        totalEnrolledStudentsAcrossAllCourses++;
    }

    // Static method to retrieve total enrolled students
    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudentsAcrossAllCourses;
    }
}

/**
 * Manages courses, enrollment, and grades.
 */
class CourseManagement {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    /**
     * Adds a new course to the system.
     */
    public static void addCourse(String code, String name, int capacity) {
        Course newCourse = new Course(code, name, capacity);
        courses.add(newCourse);
    }

    /**
     * Enrolls a student in a course.
     */
    public static boolean enrollStudent(Student student, Course course) {
        if (course.getCurrentEnrollment() < course.getMaxCapacity()) {
            student.enrollInCourse(course);
            if (!students.contains(student)) {
                students.add(student);
            }
            return true;
        }
        return false; // Capacity reached
    }

    /**
     * Assigns a grade to a student for a course.
     */
    public static void assignGrade(Student student, Course course, double grade) {
        student.assignGrade(course, grade);
    }

    /**
     * Calculates the overall average grade for a student.
     */
    public static double calculateOverallGrade(Student student) {
        Map<Course, Double> grades = student.getGrades();
        if (grades.isEmpty()) return 0.0;

        double sum = 0;
        for (double grade : grades.values()) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public static List<Course> getCourses() { return courses; }
    public static List<Student> getStudents() { return students; }

    public static Course findCourseByCode(String code) {
        for (Course c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(code)) return c;
        }
        return null;
    }

    public static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) return s;
        }
        return null;
    }
}

/**
 * Main class providing the Administrator Interface.
 */
public class CourseEnrollmentSystem {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Course Enrollment and Grade Management System ---");
            System.out.println("1. Add New Course");
            System.out.println("2. Enroll Student");
            System.out.println("3. Assign Grade");
            System.out.println("4. Calculate Overall Grades");
            System.out.println("5. View Total Enrollment Statistics");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleAddCourse();
                    break;
                case 2:
                    handleEnrollStudent();
                    break;
                case 3:
                    handleAssignGrade();
                    break;
                case 4:
                    handleCalculateGrades();
                    break;
                case 5:
                    System.out.println("Total students enrolled across all courses: " + Course.getTotalEnrolledStudents());
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void handleAddCourse() {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        System.out.print("Enter maximum capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine());

        CourseManagement.addCourse(code, name, capacity);
        System.out.println("Course added successfully.");
    }

    private static void handleEnrollStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter course code to enroll in: ");
        String courseCode = scanner.nextLine();

        Course course = CourseManagement.findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Error: Course not found.");
            return;
        }

        Student student = CourseManagement.findStudentById(id);
        if (student == null) {
            student = new Student(name, id);
        }

        if (CourseManagement.enrollStudent(student, course)) {
            System.out.println("Student enrolled successfully.");
        } else {
            System.out.println("Error: Course is at full capacity.");
        }
    }

    private static void handleAssignGrade() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter grade: ");
        double grade = Double.parseDouble(scanner.nextLine());

        Student student = CourseManagement.findStudentById(id);
        Course course = CourseManagement.findCourseByCode(courseCode);

        if (student != null && course != null) {
            CourseManagement.assignGrade(student, course, grade);
            System.out.println("Grade assigned successfully.");
        } else {
            System.out.println("Error: Student or Course not found.");
        }
    }

    private static void handleCalculateGrades() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        Student student = CourseManagement.findStudentById(id);

        if (student != null) {
            double overall = CourseManagement.calculateOverallGrade(student);
            System.out.println("Overall Grade for " + student.getName() + ": " + overall);
        } else {
            System.out.println("Error: Student not found.");
        }
    }
}
