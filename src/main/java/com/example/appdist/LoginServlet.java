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
        Connection connection = null;
        String query = "SELECT * FROM users WHERE email = ?";
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                String errorMessage = "Please fill all the required fields";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            connection = DBConfiguration.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                String errorMessage = "Email or password is incorrect";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            boolean isMatch = PasswordHashing.verifyPassword(password, resultSet.getString("password"));
            if (!isMatch) {
                String errorMessage = "Email or password is incorrect";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            User user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("name"),
                    resultSet.getString("phone"),
                    resultSet.getString("type")
            );

            request.getSession().setAttribute("token", user);
            response.sendRedirect(request.getContextPath() + "/Home");

        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}