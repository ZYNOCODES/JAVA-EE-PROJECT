package com.example.appdist;

import java.io.*;
import java.sql.*;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.util.PasswordHashing;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "SignUpServletServlet", value = "/SignUp")
public class SignUpServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String SELECTquery = "SELECT * FROM users WHERE email = ?";
        String INSERTquery = "insert into users(name, email, password, phone) values(?,?,?,?)";
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String phone = request.getParameter("contact");

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phone.isEmpty()) {
                String errorMessage = "Please fill all the required fields";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }
            if (password == null || password.length() < 6) {
                String errorMessage = "Please enter a valid password";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }
            if (!password.equals(confirmPassword)) {
                String errorMessage = "Passwords do not match";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }

            connection = DBConfiguration.getConnection();

            PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
            SELECTStatement.setString(1, email);
            ResultSet SELECTresultSet = SELECTStatement.executeQuery();
            if (SELECTresultSet.next()) {
                String errorMessage = "Email already exists";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }

            PreparedStatement INSERTStatement = connection.prepareStatement(INSERTquery);
            INSERTStatement.setString(1, name);
            INSERTStatement.setString(2, email);
            String hash = PasswordHashing.hashPassword(password);
            INSERTStatement.setString(3, hash);
            INSERTStatement.setString(4, phone);

            int rowCount = INSERTStatement.executeUpdate();
            if (rowCount > 0) {
                request.setAttribute("status", "success");
                request.setAttribute("message", "You have successfully registered!");
                response.sendRedirect(request.getContextPath() + "/Login");
            }else {
                request.setAttribute("status", "failed");
                request.setAttribute("message", "Something went wrong!");
                response.sendRedirect(request.getContextPath() + "/SignUp");
            }

        }catch (Exception err){
            err.printStackTrace();
        }finally {
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