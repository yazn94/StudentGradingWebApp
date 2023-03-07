package com.example.studentgradingsystemservlets.Servlets;

import com.example.studentgradingsystemservlets.Database.DAO;
import com.example.studentgradingsystemservlets.Model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterNewStudentServlet", value = "/done-adding-new-student")
public class AddingNewStudentServlet extends HttpServlet {
    private DAO dao = new DAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dao.addStudent(
                new Student(
                        request.getParameter("firstName"),
                        request.getParameter("lastName"),
                        Integer.parseInt(request.getParameter("age")),
                        Long.parseLong(request.getParameter("phone"))
                )
        );
        request.getRequestDispatcher("add-new-student.jsp").forward(request, response);
    }
}
