package com.example.studentgradingsystemservlets.Servlets;

import com.example.studentgradingsystemservlets.Database.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddingNewGradeServlet", value = "/done-adding-new-grade")
public class AddingNewGradeServlet extends HttpServlet {
    DAO dao = new DAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao.addGrade(
                Long.parseLong(request.getParameter("studentId")),
                Long.parseLong(request.getParameter("courseId")),
                Integer.parseInt(request.getParameter("grade"))
        );
        request.getRequestDispatcher("add-new-grade.jsp").forward(request, response);
    }
}
