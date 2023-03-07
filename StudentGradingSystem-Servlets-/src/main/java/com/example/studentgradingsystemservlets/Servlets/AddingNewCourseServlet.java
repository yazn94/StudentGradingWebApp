package com.example.studentgradingsystemservlets.Servlets;

import com.example.studentgradingsystemservlets.Database.DAO;
import com.example.studentgradingsystemservlets.Model.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddingNewCourseServlet", value = "/done-adding-course")
public class AddingNewCourseServlet extends HttpServlet {
    DAO dao = new DAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao.addCourse(new Course(request.getParameter("name")));
        request.getRequestDispatcher("add-new-course.jsp").forward(request, response);
    }
}
