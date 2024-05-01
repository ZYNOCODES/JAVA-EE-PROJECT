package com.example.appdist;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.Models.Post;
import com.example.appdist.Models.User;
import com.example.appdist.Models.UserDAO.UserDAO;
import com.example.appdist.Models.UserDAO.UserDAOImpl;
import com.example.appdist.util.PasswordHashing;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                String errorMessage = "Please fill all the required fields";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            User user = new User(email, password);
            UserDAO userDAO = new UserDAOImpl();
            User result = userDAO.login(user);
            if (result == null) {
                String errorMessage = "Email or password is incorrect";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            request.getSession().setAttribute("token", result);
            response.sendRedirect(request.getContextPath() + "/Home");

        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

}