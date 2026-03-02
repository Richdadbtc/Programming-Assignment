import java.util.Scanner;

public class StudentManagementSystem {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Record Management System ---");
            System.out.println("1. Add New Student");
            System.out.println("2. Update Student Information");
            System.out.println("3. View Student Details");
            System.out.println("4. View All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = readMenuChoice();

            switch (choice) {
                case 1:
                    handleAddStudent();
                    break;
                case 2:
                    handleUpdateStudent();
                    break;
                case 3:
                    handleViewStudent();
                    break;
                case 4:
                    handleViewAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int readMenuChoice() {
        int choice = -1;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return choice;
    }

    private static void handleAddStudent() {
        System.out.print("Enter student ID: ");
        String id = readNonEmptyString();

        System.out.print("Enter student name: ");
        String name = readNonEmptyString();

        System.out.print("Enter student age: ");
        int age = readPositiveInt();

        System.out.print("Enter student grade (0-100): ");
        double grade = readGrade();

        boolean added = StudentManagement.addStudent(id, name, age, grade);
        if (added) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Error: Student with this ID already exists or storage is full.");
        }
    }

    private static void handleUpdateStudent() {
        System.out.print("Enter student ID to update: ");
        String id = readNonEmptyString();

        Student student = StudentManagement.findStudentById(id);
        if (student == null) {
            System.out.println("Error: Student ID not found.");
            return;
        }

        System.out.println("Current details:");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());
        System.out.println("Grade: " + student.getGrade());

        System.out.print("Enter new name (leave blank to keep current): ");
        String nameInput = scanner.nextLine().trim();
        String newName = nameInput.isEmpty() ? student.getName() : nameInput;

        System.out.print("Enter new age (leave blank to keep current): ");
        String ageInput = scanner.nextLine().trim();
        int newAge = student.getAge();
        if (!ageInput.isEmpty()) {
            try {
                int parsedAge = Integer.parseInt(ageInput);
                if (parsedAge > 0) {
                    newAge = parsedAge;
                } else {
                    System.out.println("Invalid age. Keeping current age.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid age format. Keeping current age.");
            }
        }

        System.out.print("Enter new grade (0-100, leave blank to keep current): ");
        String gradeInput = scanner.nextLine().trim();
        double newGrade = student.getGrade();
        if (!gradeInput.isEmpty()) {
            try {
                double parsedGrade = Double.parseDouble(gradeInput);
                if (parsedGrade >= 0 && parsedGrade <= 100) {
                    newGrade = parsedGrade;
                } else {
                    System.out.println("Invalid grade range. Keeping current grade.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid grade format. Keeping current grade.");
            }
        }

        boolean updated = StudentManagement.updateStudent(id, newName, newAge, newGrade);
        if (updated) {
            System.out.println("Student information updated successfully.");
        } else {
            System.out.println("Error: Unable to update student information.");
        }
    }

    private static void handleViewStudent() {
        System.out.print("Enter student ID to view: ");
        String id = readNonEmptyString();

        Student student = StudentManagement.findStudentById(id);
        if (student == null) {
            System.out.println("Error: Student ID not found.");
        } else {
            System.out.println("Student Details:");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Grade: " + student.getGrade());
        }
    }

    private static void handleViewAllStudents() {
        Student[] students = StudentManagement.getAllStudents();
        if (students.length == 0) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n--- All Students ---");
        for (Student student : students) {
            System.out.println("ID: " + student.getId()
                    + ", Name: " + student.getName()
                    + ", Age: " + student.getAge()
                    + ", Grade: " + student.getGrade());
        }
    }

    private static String readNonEmptyString() {
        while (true) {
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.print("Input cannot be empty. Please try again: ");
        }
    }

    private static int readPositiveInt() {
        while (true) {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value > 0) {
                    return value;
                }
                System.out.print("Please enter a positive number: ");
            } else {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
        }
    }

    private static double readGrade() {
        while (true) {
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (value >= 0 && value <= 100) {
                    return value;
                }
                System.out.print("Please enter a grade between 0 and 100: ");
            } else {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
        }
    }
}

class Student {
    private String id;
    private String name;
    private int age;
    private double grade;

    public Student(String id, String name, int age, double grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}

class StudentManagement {
    private static final int MAX_STUDENTS = 100;
    private static Student[] students = new Student[MAX_STUDENTS];
    private static int totalStudents = 0;

    public static boolean addStudent(String id, String name, int age, double grade) {
        if (findStudentById(id) != null) {
            return false;
        }
        if (totalStudents >= MAX_STUDENTS) {
            return false;
        }
        students[totalStudents] = new Student(id, name, age, grade);
        totalStudents++;
        return true;
    }

    public static boolean updateStudent(String id, String name, int age, double grade) {
        for (int i = 0; i < totalStudents; i++) {
            Student student = students[i];
            if (student.getId().equals(id)) {
                student.setName(name);
                student.setAge(age);
                student.setGrade(grade);
                return true;
            }
        }
        return false;
    }

    public static Student findStudentById(String id) {
        for (int i = 0; i < totalStudents; i++) {
            Student student = students[i];
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public static Student[] getAllStudents() {
        Student[] result = new Student[totalStudents];
        for (int i = 0; i < totalStudents; i++) {
            result[i] = students[i];
        }
        return result;
    }
}

