package org.example.Server;

import org.example.Database.DAO;
import org.example.Model.Course;
import org.example.Model.Grade;
import org.example.Model.Student;
import org.example.Model.UserCredentials;
import org.example.Services.Authentication;
import org.example.Services.Printer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.List;

public class Server {

    public static void main(String[] args) {
        System.out.println("Server has started at " + new Date());
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

    //   A thread class for handling multiple connections concurrently
    private static class ClientHandler extends Thread {
        private Socket socket;
        private DataInputStream inputFromClient;
        private DataOutputStream outputToClient;
        private Printer printer;

        private DAO dao = new DAO();

        public ClientHandler(Socket socket) {
            try {
                this.socket = socket;
                inputFromClient = new DataInputStream(socket.getInputStream());
                outputToClient = new DataOutputStream(socket.getOutputStream());
                printer = new Printer(inputFromClient, outputToClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void courseAvg() {
            printer.printHeader("Course Average");
            try {
                outputToClient.writeUTF("Enter the course ID: ");
                Long courseId = Long.parseLong(inputFromClient.readUTF());
                double avg = dao.courseAvg(courseId);
                outputToClient.writeUTF("The average for the course is: " + avg);
                printer.clickEnter();
                studentGradingSystemPage();

                System.out.println("Course average: " + avg + " for course ID: " + courseId +
                        "at " + new Date());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                // Display the client's host name and IP address
                System.out.println("Client " + socket.getInetAddress().getHostAddress() + " connected at " + new Date());
                while (true) {
                    register();
                    studentGradingSystemPage();
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        }

        public void addNewCourse() {
            try {
                printer.printHeader("Add New Course");
                outputToClient.writeUTF("Enter the name of the course: ");
                String courseName = inputFromClient.readUTF();

                dao.addCourse(new Course(courseName));
                outputToClient.writeUTF("Course added successfully");
                System.out.println("Course added successfully");

                studentGradingSystemPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void register() {
            while (true) {
                try {
                    printer.printHeader("Registration");

                    outputToClient.writeUTF("Enter your email: ");
                    String email = inputFromClient.readUTF();
                    outputToClient.writeUTF("Enter your password: ");
                    String password = inputFromClient.readUTF();

                    boolean auth = Authentication.authenticate(new UserCredentials(email, password));
                    if (auth) {
                        System.out.println("Registration successful at " + new Date() + " for email: " + email);
                        return;
                    } else {
                        outputToClient.writeUTF("Registration Failed!\n" +
                                "Please, if you are an academic staff member make sure you've" +
                                " entered the correct email and password.");
                        outputToClient.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void studentGradingSystemPage() {
            try {
                printer.printHeader("Student Grading System");
                outputToClient.writeUTF("Welcome to the student grading system");
                outputToClient.writeUTF("Please, select an option: ");
                outputToClient.writeUTF("1) Add a new student");
                outputToClient.writeUTF("2) Add a new course");
                outputToClient.writeUTF("3) Add a new grade");
                outputToClient.writeUTF("4) Show all students");
                outputToClient.writeUTF("5) Show all courses");
                outputToClient.writeUTF("6) Show all courses for a student (with grades) ");
                outputToClient.writeUTF("7) Show all students in a course (with grades) ");
                outputToClient.writeUTF("8) Show course Average");

                String option = inputFromClient.readUTF();

                switch (option) {
                    case "1":
                        addNewStudent();
                        break;
                    case "2":
                        addNewCourse();
                        break;
                    case "3":
                        addNewGrade();
                        break;
                    case "4":
                        showAllStudents();
                        break;
                    case "5":
                        showAllCourses();
                        break;
                    case "6":
                        allCoursesForStudent();
                        break;
                    case "7":
                        allStudentsInCourse();
                        break;
                    case "8":
                        courseAvg();
                        break;
                    default:
                        outputToClient.writeUTF("Please, enter a valid option");
                        studentGradingSystemPage();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public boolean onlyDigits(String s) {
            return s.matches("[0-9]+");
        }


        public void addNewGrade() {
            try {
                printer.printHeader("Add New Grade");
                String read;

//              Reading the student id
                outputToClient.writeUTF("Enter student id: ");
                read = inputFromClient.readUTF();
                if (!onlyDigits(read)) {
                    outputToClient.writeUTF("Please enter a valid student id");
                    addNewGrade();
                }
                Long studentId = Long.parseLong(read);

//              Reading the course id
                outputToClient.writeUTF("Enter course id: ");
                read = inputFromClient.readUTF();
                if (!onlyDigits(read)) {
                    outputToClient.writeUTF("Please enter a valid course id");
                    addNewGrade();
                }
                Long courseId = Long.parseLong(read);

//              Reading the grade
                outputToClient.writeUTF("Enter grade: ");
                read = inputFromClient.readUTF();
                if (!onlyDigits(read)) {
                    outputToClient.writeUTF("Please enter a valid grade");
                    addNewGrade();
                }
                int grade = Integer.parseInt(read);

                dao.addGrade(studentId, courseId, grade);
                outputToClient.writeUTF("Grade added successfully");

                System.out.println("Grade added successfully");
                studentGradingSystemPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void addNewStudent() {
            try {
                printer.printHeader("Add New Student");

                outputToClient.writeUTF("Enter the student's first name: ");
                String firstName = inputFromClient.readUTF();

                outputToClient.writeUTF("Enter the student's last name: ");
                String lastName = inputFromClient.readUTF();

                outputToClient.writeUTF("Enter the student's age: ");
                String read = inputFromClient.readUTF();

                if (!onlyDigits(read)) {
                    outputToClient.writeUTF("Please, enter a valid age");
                    addNewStudent();
                    return;
                }
                int age = Integer.parseInt(read);

                outputToClient.writeUTF("Enter the student's phone number: ");
                read = inputFromClient.readUTF();
                if (!onlyDigits(read)) {
                    outputToClient.writeUTF("Please, enter a valid phone number (only numbers)");
                    addNewStudent();
                    return;
                }

                Long phoneNumber = Long.parseLong(read);
                Student student = new Student(firstName, lastName, age, phoneNumber);
                dao.addStudent(student);
                System.out.println("Student added successfully at " + new Date());
                outputToClient.writeUTF("Student added successfully");
                studentGradingSystemPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void showAllStudents() throws IOException {
            printer.printHeader("All Students");
            printer.printAllStudents(dao.allStudents());
            printer.clickEnter();
            System.out.println("All students shown at " + new Date());
            studentGradingSystemPage();
        }

        public void showAllCourses() {
            printer.printHeader("All Courses");
            printer.printAllCourses(dao.allCourses());
            printer.clickEnter();
            System.out.println("All courses shown at " + new Date());
            studentGradingSystemPage();
        }

        public void allStudentsInCourse() {
            try {
                printer.printHeader("All Students in a Course");
                outputToClient.writeUTF("Please enter the course ID:");

                // read course ID from client
                Long courseId = Long.parseLong(inputFromClient.readUTF());

                // get list of students in the course
                List<Grade> studentsWithGrade = dao.getStudentsInCourse(courseId);
                outputToClient.flush();
                printer.printStudentsWithGrades(studentsWithGrade);
                printer.clickEnter();
                studentGradingSystemPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void allCoursesForStudent() {
            try {
                printer.printHeader("All Courses for a Student");
                outputToClient.writeUTF("Enter student id: ");
                String studentIdStr = inputFromClient.readUTF();
                Long studentId = Long.parseLong(studentIdStr);

                List<Grade> grades = dao.getFinishedCoursesForStudent(studentId);
                outputToClient.flush();
                printer.printCoursesWithGrades(grades);

                printer.clickEnter();
                studentGradingSystemPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

