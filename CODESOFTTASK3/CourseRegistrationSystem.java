package CODESOFTTASK3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<Student> enrolledStudents;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean addStudent(Student student) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }

    public boolean removeStudent(Student student) {
        return enrolledStudents.remove(student);
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents.size();
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", enrolledStudents=" + enrolledStudents.size() +
                '}';
    }
}

class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerForCourse(Course course) {
        if (!registeredCourses.contains(course) && course.addStudent(this)) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course) && course.removeStudent(this)) {
            registeredCourses.remove(course);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", name='" + name + '\'' +
                ", registeredCourses=" + registeredCourses.size() +
                '}';
    }
}

public class CourseRegistrationSystem {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCourse Registration System");
            System.out.println("1. Add Course");
            System.out.println("2. Display Available Courses");
            System.out.println("3. Register Student for a Course");
            System.out.println("4. Drop Course");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addCourse(scanner);
                    break;
                case 2:
                    displayAvailableCourses();
                    break;
                case 3:
                    registerStudent(scanner);
                    break;
                case 4:
                    dropCourse(scanner);
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCourse(Scanner scanner) {
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Course Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Course Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Course course = new Course(code, title, description, capacity);
        courses.add(course);
        System.out.println("Course added successfully.");
    }

    private static void displayAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course.getCode() + " - " + course.getTitle() +
                    " (Available Slots: " + course.getAvailableSlots() + ")");
        }
    }

    private static void registerStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        Student student = new Student(studentID, name);
        students.add(student);

        displayAvailableCourses();

        System.out.print("Enter Course Code to Register: ");
        String courseCode = scanner.nextLine();

        Course selectedCourse = findCourseByCode(courseCode);
        if (selectedCourse != null) {
            if (selectedCourse.addStudent(student)) {
                student.registerForCourse(selectedCourse);
                System.out.println("Student registered successfully for " + courseCode);
            } else {
                System.out.println("Course is full. Student registration failed.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();

        Student student = findStudentByID(studentID);
        if (student != null) {
            System.out.println("Courses registered by " + student.getName() + ":");
            for (Course course : student.getRegisteredCourses()) {
                System.out.println(course.getCode() + " - " + course.getTitle());
            }

            System.out.print("Enter Course Code to Drop: ");
            String courseCode = scanner.nextLine();

            Course selectedCourse = findCourseByCode(courseCode);
            if (selectedCourse != null) {
                if (student.dropCourse(selectedCourse)) {
                    System.out.println("Student dropped the course successfully.");
                } else {
                    System.out.println("Student is not registered for this course.");
                }
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private static Student findStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }
}

