package com.example.studentgradingsystemspring;


import com.example.studentgradingsystemspring.Database.DAO;
import com.example.studentgradingsystemspring.Model.Course;
import com.example.studentgradingsystemspring.Model.Student;
import com.example.studentgradingsystemspring.Model.UserCredentials;
import com.example.studentgradingsystemspring.Services.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SystemController {
    DAO dao;

    @Autowired
    public SystemController(DAO dao) {
        this.dao = dao;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("go-to-register")
    public String login() {
        return "registration";
    }

    @PostMapping("authenticate")
    public String authenticate(@RequestParam String email, @RequestParam String password) {
        boolean auth = Authentication.authenticate(new UserCredentials(email, password));
        if (auth) {
            return "student-grading-system";
        } else {
            return "registration-failed";
        }
    }

    @GetMapping("grading-system")
    public String gradingSystem() {
        return "student-grading-system";
    }

    @GetMapping("add-new-student")
    public String addNewStudent() {
        return "add-new-student";
    }

    @PostMapping("done-adding-new-student")
    public String doneAddingNewStudent(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age, @RequestParam long phone) {
        dao.addStudent(new Student(firstName, lastName, age, phone));
        return "add-new-student";
    }

    @GetMapping("add-new-course")
    public String addNewCourse() {
        return "add-new-course";
    }

    @PostMapping("done-adding-course")
    public String doneAddingCourse(@RequestParam String name) {
        dao.addCourse(new Course(name));
        return "add-new-course";
    }

    @GetMapping("add-new-grade")
    public String addNewGrade() {
        return "add-new-grade";
    }


    @PostMapping("done-adding-new-grade")
    public String doneAddingNewGrade(@RequestParam Long studentId, @RequestParam Long courseId, @RequestParam int grade) {
        dao.addGrade(studentId, courseId, grade);
        return "add-new-grade";
    }

    @GetMapping("all-students")
    public String allStudents(Model model) {
        model.addAttribute("students", dao.allStudents());
        return "all-students";
    }

    @GetMapping("all-courses")
    public String allCourses(Model model) {
        model.addAttribute("courses", dao.allCourses());
        return "all-courses";
    }

    @GetMapping("courses-for-student")
    public String coursesForStudent(Model model) {
        return "courses-for-student";
    }

    @PostMapping("view-courses-for-student")
    public String viewCoursesForStudent(@RequestParam Long studentId, Model model) {
        model.addAttribute("student", dao.getStudentById(studentId));
        model.addAttribute("grades", dao.getFinishedCoursesForStudent(studentId));
        return "view-courses-for-student";
    }

    @GetMapping("students-in-course")
    public String studentsInCourse(Model model) {
        return "students-in-course";
    }


    @PostMapping("view-students-in-course")
    public String viewStudentsInCourse(@RequestParam Long courseId, Model model) {
        model.addAttribute("course", dao.getCourseById(courseId));
        model.addAttribute("grades", dao.getStudentsInCourse(courseId));
        return "view-students-in-course";
    }

    @GetMapping("course-average")
    public String courseAverage(Model model) {
        return "course-average";

    }

    @PostMapping("view-course-average")
    public String viewCourseAverage(@RequestParam Long courseId, Model model) {
        model.addAttribute("course", dao.getCourseById(courseId).getName());
        model.addAttribute("average", dao.courseAvg(courseId));
        return "view-course-average";
    }

}
