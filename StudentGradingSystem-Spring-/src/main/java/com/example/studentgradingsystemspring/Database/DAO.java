package com.example.studentgradingsystemspring.Database;


import com.example.studentgradingsystemspring.Model.Course;
import com.example.studentgradingsystemspring.Model.Grade;
import com.example.studentgradingsystemspring.Model.Student;
import com.example.studentgradingsystemspring.Model.UserCredentials;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Configuration

public class DAO {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/studentGradingDB";

    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "123456";

    // SQL statements
    private static final String INSERT_STUDENT =
            "INSERT INTO students(firstName, lastName, age, phone) VALUES (?, ?, ?, ?)";
    private static final String INSERT_COURSE = "INSERT INTO courses(name) VALUES (?)";
    private static final String SHOW_STUDENTS = "SELECT * FROM students";
    private static final String SHOW_COURSES = "SELECT * FROM courses";

    private static final String SHOW_ACADEMIC_STAFF = "SELECT * FROM AcademicStaffCredentials";

    private static final String ADD_GRADE =
            "INSERT INTO grades(studentId, courseId, grade) VALUES (?, ?, ?)";
    private static final String COURSES_FOR_STUDENT = "SELECT name, grade\n" +
            "FROM\n" +
            "    courses INNER JOIN grades ON courses.id = grades.courseId\n" +
            "WHERE grades.studentId = (?)";

    private static final String STUDENTS_IN_COURSE = "SELECT firstName, lastName, grade \n" +
            "FROM\n" +
            "    students INNER JOIN grades ON students.id = grades.studentId\n" +
            "WHERE grades.courseId = (?)\n";


    private Connection conn;

    public DAO() {
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Double courseAvg(Long courseId) {
        try (PreparedStatement ps = conn.prepareStatement(STUDENTS_IN_COURSE)) {
            ps.setLong(1, courseId);
            ResultSet rs = ps.executeQuery();
            Double sum = .0;
            int count = 0;
            while (rs.next()) {
                sum += rs.getInt("grade");
                count++;
            }
            return sum / count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Course getCourseById(Long id) {
        try (PreparedStatement ps = conn.prepareStatement(SHOW_COURSES)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getLong("id") == id) {
                    Course course = new Course(
                            rs.getLong("id"),
                            rs.getString("name")
                    );
                    return course;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Course not found");
    }

    public void addStudent(Student student) {
        try (PreparedStatement ps = conn.prepareStatement(INSERT_STUDENT)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getAge());
            ps.setString(4, String.valueOf(student.getPhone()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCourse(Course course) {
        try (PreparedStatement ps = conn.prepareStatement(INSERT_COURSE)) {
            ps.setString(1, course.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> allStudents() {
        List<Student> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(SHOW_STUDENTS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student(
                        rs.getLong("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getLong("phone")
                );
                list.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Course> allCourses() {
        List<Course> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(SHOW_COURSES)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Course course = new Course(
                        rs.getLong("id"),
                        rs.getString("name")
                );
                list.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<UserCredentials> allAcademicStaff() {
        List<UserCredentials> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(SHOW_ACADEMIC_STAFF)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserCredentials userCredentials = new UserCredentials(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                list.add(userCredentials);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void addGrade(Long StudentId, Long CourseId, int grade) {
        try (PreparedStatement ps = conn.prepareStatement(ADD_GRADE)) {
            ps.setLong(1, StudentId);
            ps.setLong(2, CourseId);
            ps.setInt(3, grade);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getStudentById(Long id) {
        try (PreparedStatement ps = conn.prepareStatement(SHOW_STUDENTS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getLong("id") == id) {
                    Student student = new Student(
                            rs.getLong("id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getInt("age"),
                            rs.getLong("phone")
                    );
                    return student;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // if student not found
        throw new RuntimeException("Student not found");
    }

    public List<Grade> getFinishedCoursesForStudent(Long studentId) {
        List<Grade> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(COURSES_FOR_STUDENT)) {
            ps.setString(1, String.valueOf(studentId));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Grade grade = new Grade();
                grade.setCourseName(rs.getString("name"));
                grade.setGrade(rs.getInt("grade"));

                list.add(grade);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Grade> getStudentsInCourse(Long courseId) {
        List<Grade> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(STUDENTS_IN_COURSE)) {
            ps.setString(1, String.valueOf(courseId));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setStudentFirstName(rs.getString("firstName"));
                grade.setStudentLastName(rs.getString("lastName"));
                grade.setGrade(rs.getInt("grade"));
                list.add(grade);
            }
        } catch (SQLException e) {
            throw new RuntimeException("from the dao, students in course");
        }
        return list;
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
