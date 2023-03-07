package org.example.Services;

import org.example.Model.Course;
import org.example.Model.Grade;
import org.example.Model.Student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;

public class Printer {
    private DataInputStream inputFromClient;
    private DataOutputStream outputToClient;

    public Printer(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.inputFromClient = dataInputStream;
        this.outputToClient = dataOutputStream;
    }

    public void printHeader(String header) {
        try {
            outputToClient.writeUTF("download");
            outputToClient.writeUTF("\n\n*************************************************************");
            outputToClient.writeUTF("                 * " + header + " *");
            outputToClient.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printAllStudents(List<Student> students) {
        try {
            if (students.isEmpty()) {
                outputToClient.writeUTF("No students found");
            } else {
                outputToClient.writeUTF(String.format("%-20s %-20s %-10s %-15s\n", "First Name", "Last Name", "Age", "Phone Number"));
                outputToClient.writeUTF("---------------------------------------------------------------------");
                for (Student student : students) {
                    outputToClient.writeUTF(String.format("%-20s %-20s %-10d %-15d\n",
                            student.getFirstName(), student.getLastName(), student.getAge(), student.getPhone()));
                }
                outputToClient.flush();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printAllCourses(List<Course> courses) {
        try {
            if (courses.isEmpty()) {
                outputToClient.writeUTF("No courses found");
            } else {
                outputToClient.writeUTF(String.format("%-10s %-20s\n", "ID", "Name"));
                outputToClient.writeUTF("-------------------------------");
                for (Course course : courses) {
                    outputToClient.writeUTF(String.format("%-10s %-20s\n", course.getId(), course.getName()));
                }
                outputToClient.flush();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printCoursesWithGrades(List<Grade> grades) {
        try {
            if (grades.isEmpty()) {
                outputToClient.writeUTF("No courses found for student");
            } else {
                outputToClient.flush();
                outputToClient.writeUTF(String.format("%-20s %-10s", "Course Name", "Grade"));
                outputToClient.writeUTF("--------------------------------------------");


                for (Grade grade : grades) {
                    outputToClient.flush();
                    outputToClient.writeUTF(String.format("%-20s %-10d", grade.getCourseName(), grade.getGrade()));
                    outputToClient.flush();
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printStudentsWithGrades(List<Grade> grades) {
        try {
            if (grades.isEmpty()) {
                outputToClient.writeUTF("No students found for this course");
            } else {
                outputToClient.flush();
                outputToClient.writeUTF(String.format("%-20s %-20s %-10s", "First Name", "Last Name", "Grade"));
                outputToClient.writeUTF("-------------------------------------------------------------");
                for (Grade grade : grades) {
                    outputToClient.flush();
                    outputToClient.writeUTF(String.format("%-20s %-20s %-10d", grade.getStudentFirstName(), grade.getStudentLastName(), grade.getGrade()));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clickEnter() {
        try {
            outputToClient.writeUTF("Press enter to come back!");
            String read = inputFromClient.readUTF();
            outputToClient.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
