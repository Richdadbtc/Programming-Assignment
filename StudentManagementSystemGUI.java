import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Student Management System GUI application.
 * This class provides a graphical interface for administrators to manage students,
 * courses, and grades using Java Swing.
 */
public class StudentManagementSystemGUI extends JFrame {

    // Data Storage
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    // GUI Components
    private JTabbedPane tabbedPane;
    
    // Student Tab Components
    private JTable studentTable;
    private DefaultTableModel studentTableModel;
    private JTextField studentIdField, studentNameField;
    private JButton addStudentBtn, updateStudentBtn;

    // Enrollment Tab Components
    private JComboBox<Course> courseComboBox;
    private DefaultListModel<Student> eligibleStudentsListModel;
    private JList<Student> eligibleStudentsList;
    private JButton enrollBtn;

    // Grade Tab Components
    private JComboBox<Student> studentGradeComboBox;
    private DefaultListModel<String> enrolledCoursesListModel;
    private JList<String> enrolledCoursesList;
    private JTextField gradeField;
    private JButton assignGradeBtn;

    public StudentManagementSystemGUI() {
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize sample data
        initSampleData();

        // Create the main tabbed pane
        tabbedPane = new JTabbedPane();

        // Add tabs
        tabbedPane.addTab("Student Management", createStudentTab());
        tabbedPane.addTab("Course Enrollment", createEnrollmentTab());
        tabbedPane.addTab("Grade Management", createGradeTab());

        add(tabbedPane);
        
        // Finalize UI
        setVisible(true);
    }

    private void initSampleData() {
        courses.add(new Course("CS101", "Introduction to Computer Science"));
        courses.add(new Course("MATH201", "Calculus I"));
        courses.add(new Course("ENG102", "English Composition"));
        
        // Add a few sample students
        Student s1 = new Student("1001", "Alice Smith");
        Student s2 = new Student("1002", "Bob Johnson");
        students.add(s1);
        students.add(s2);
    }

    /**
     * Creates the Student Management Tab.
     * Provides functionality to add, update, and view students.
     */
    private JPanel createStudentTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top Panel: Input fields
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        inputPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        inputPanel.add(studentIdField);
        
        inputPanel.add(new JLabel("Student Name:"));
        studentNameField = new JTextField();
        inputPanel.add(studentNameField);

        addStudentBtn = new JButton("Add Student");
        updateStudentBtn = new JButton("Update Student");
        inputPanel.add(addStudentBtn);
        inputPanel.add(updateStudentBtn);

        panel.add(inputPanel, BorderLayout.NORTH);

        // Center Panel: Student Table
        String[] columnNames = {"ID", "Name"};
        studentTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentTable = new JTable(studentTableModel);
        updateStudentTable();
        
        JScrollPane scrollPane = new JScrollPane(studentTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Event Handlers
        addStudentBtn.addActionListener(e -> handleAddStudent());
        updateStudentBtn.addActionListener(e -> handleUpdateStudent());

        // Selection listener to fill fields when a row is clicked
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow != -1) {
                studentIdField.setText(studentTableModel.getValueAt(selectedRow, 0).toString());
                studentNameField.setText(studentTableModel.getValueAt(selectedRow, 1).toString());
                studentIdField.setEditable(false); // ID should not be updated
            }
        });

        return panel;
    }

    /**
     * Creates the Course Enrollment Tab.
     * Allows enrolling students in selected courses.
     */
    private JPanel createEnrollmentTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top: Course Selection
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Select Course:"));
        courseComboBox = new JComboBox<>(courses.toArray(new Course[0]));
        topPanel.add(courseComboBox);
        panel.add(topPanel, BorderLayout.NORTH);

        // Center: Eligible Students List
        eligibleStudentsListModel = new DefaultListModel<>();
        eligibleStudentsList = new JList<>(eligibleStudentsListModel);
        updateEligibleStudentsList();
        
        JScrollPane scrollPane = new JScrollPane(eligibleStudentsList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Eligible Students (Not Enrolled)"));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Bottom: Enroll Button
        enrollBtn = new JButton("Enroll Selected Student");
        panel.add(enrollBtn, BorderLayout.SOUTH);

        // Event Handlers
        courseComboBox.addActionListener(e -> updateEligibleStudentsList());
        enrollBtn.addActionListener(e -> handleEnrollment());

        return panel;
    }

    /**
     * Creates the Grade Management Tab.
     * Allows assigning grades to students for enrolled courses.
     */
    private JPanel createGradeTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top: Student Selection
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Select Student:"));
        studentGradeComboBox = new JComboBox<>();
        updateStudentGradeComboBox();
        topPanel.add(studentGradeComboBox);
        panel.add(topPanel, BorderLayout.NORTH);

        // Center: Enrolled Courses List
        enrolledCoursesListModel = new DefaultListModel<>();
        enrolledCoursesList = new JList<>(enrolledCoursesListModel);
        JScrollPane scrollPane = new JScrollPane(enrolledCoursesList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Enrolled Courses & Grades"));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Bottom: Grade Assignment
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField(5);
        bottomPanel.add(gradeField);
        assignGradeBtn = new JButton("Assign Grade");
        bottomPanel.add(assignGradeBtn);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Event Handlers
        studentGradeComboBox.addActionListener(e -> updateEnrolledCoursesList());
        assignGradeBtn.addActionListener(e -> handleAssignGrade());

        return panel;
    }

    // --- Action Handlers ---

    private void handleAddStudent() {
        String id = studentIdField.getText().trim();
        String name = studentNameField.getText().trim();

        if (id.isEmpty() || name.isEmpty()) {
            showError("Please enter both ID and Name.");
            return;
        }

        // Check if ID exists
        if (findStudentById(id) != null) {
            showError("Student ID already exists.");
            return;
        }

        students.add(new Student(id, name));
        updateStudentTable();
        updateEligibleStudentsList();
        updateStudentGradeComboBox();
        clearStudentFields();
        JOptionPane.showMessageDialog(this, "Student added successfully.");
    }

    private void handleUpdateStudent() {
        String id = studentIdField.getText().trim();
        String name = studentNameField.getText().trim();

        Student s = findStudentById(id);
        if (s == null) {
            showError("Select a student from the table to update.");
            return;
        }

        if (name.isEmpty()) {
            showError("Name cannot be empty.");
            return;
        }

        s.setName(name);
        updateStudentTable();
        updateEligibleStudentsList();
        updateStudentGradeComboBox();
        clearStudentFields();
        JOptionPane.showMessageDialog(this, "Student information updated.");
    }

    private void handleEnrollment() {
        Course course = (Course) courseComboBox.getSelectedItem();
        Student student = eligibleStudentsList.getSelectedValue();

        if (course == null || student == null) {
            showError("Please select both a course and a student.");
            return;
        }

        student.enroll(course);
        updateEligibleStudentsList();
        updateEnrolledCoursesList();
        JOptionPane.showMessageDialog(this, "Student enrolled in " + course.getName());
    }

    private void handleAssignGrade() {
        Student student = (Student) studentGradeComboBox.getSelectedItem();
        String selectedEntry = enrolledCoursesList.getSelectedValue();
        String gradeStr = gradeField.getText().trim();

        if (student == null || selectedEntry == null || gradeStr.isEmpty()) {
            showError("Please select a student, a course, and enter a grade.");
            return;
        }

        // Parse course name from "CourseName (Grade: ...)"
        String courseName = selectedEntry.split(" \\(")[0];
        Course course = findCourseByName(courseName);

        if (course == null) {
            showError("Course not found.");
            return;
        }

        try {
            double grade = Double.parseDouble(gradeStr);
            if (grade < 0 || grade > 100) {
                showError("Grade must be between 0 and 100.");
                return;
            }
            student.assignGrade(course, grade);
            updateEnrolledCoursesList();
            gradeField.setText("");
            JOptionPane.showMessageDialog(this, "Grade assigned successfully.");
        } catch (NumberFormatException e) {
            showError("Invalid grade format. Please enter a numeric value.");
        }
    }

    // --- Dynamic UI Update Helpers ---

    private void updateStudentTable() {
        studentTableModel.setRowCount(0);
        for (Student s : students) {
            studentTableModel.addRow(new Object[]{s.getId(), s.getName()});
        }
    }

    private void updateEligibleStudentsList() {
        eligibleStudentsListModel.clear();
        Course selectedCourse = (Course) courseComboBox.getSelectedItem();
        if (selectedCourse == null) return;

        for (Student s : students) {
            if (!s.isEnrolledIn(selectedCourse)) {
                eligibleStudentsListModel.addElement(s);
            }
        }
    }

    private void updateStudentGradeComboBox() {
        studentGradeComboBox.removeAllItems();
        for (Student s : students) {
            studentGradeComboBox.addItem(s);
        }
    }

    private void updateEnrolledCoursesList() {
        enrolledCoursesListModel.clear();
        Student selectedStudent = (Student) studentGradeComboBox.getSelectedItem();
        if (selectedStudent == null) return;

        for (Course c : selectedStudent.getEnrolledCourses()) {
            Double grade = selectedStudent.getGrade(c);
            String entry = c.getName() + (grade != null ? " (Grade: " + grade + ")" : " (No Grade)");
            enrolledCoursesListModel.addElement(entry);
        }
    }

    private void clearStudentFields() {
        studentIdField.setText("");
        studentNameField.setText("");
        studentIdField.setEditable(true);
        studentTable.clearSelection();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // --- Helper Search Methods ---

    private Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    private Course findCourseByName(String name) {
        for (Course c : courses) {
            if (c.getName().equals(name)) return c;
        }
        return null;
    }

    public static void main(String[] args) {
        // Use SwingUtilities to ensure the GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(StudentManagementSystemGUI::new);
    }

    // --- Internal Data Models ---

    private static class Student {
        private String id;
        private String name;
        private List<Course> enrolledCourses = new ArrayList<>();
        private Map<Course, Double> grades = new HashMap<>();

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public void enroll(Course course) {
            if (!enrolledCourses.contains(course)) {
                enrolledCourses.add(course);
            }
        }

        public boolean isEnrolledIn(Course course) {
            return enrolledCourses.contains(course);
        }

        public List<Course> getEnrolledCourses() {
            return enrolledCourses;
        }

        public void assignGrade(Course course, double grade) {
            grades.put(course, grade);
        }

        public Double getGrade(Course course) {
            return grades.get(course);
        }

        @Override
        public String toString() {
            return name + " (" + id + ")";
        }
    }

    private static class Course {
        private String code;
        private String name;

        public Course(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() { return code; }
        public String getName() { return name; }

        @Override
        public String toString() {
            return code + " - " + name;
        }
    }
}
