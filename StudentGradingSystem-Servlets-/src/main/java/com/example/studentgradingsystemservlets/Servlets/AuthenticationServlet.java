package com.example.studentgradingsystemservlets.Servlets;

import com.example.studentgradingsystemservlets.Model.UserCredentials;
import com.example.studentgradingsystemservlets.Services.Authentication;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "StudentGradingSystemServlet", value = "/authenticate")
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCredentials userCredentials = new UserCredentials(
                request.getParameter("email"),
                request.getParameter("password")
        );
        boolean authenticated = Authentication.authenticate(userCredentials);
        if (authenticated) {
            request.getRequestDispatcher("student-grading-system.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("registration-failed.jsp").forward(request, response);
        }
    }
}
