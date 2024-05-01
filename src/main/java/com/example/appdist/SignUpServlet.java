package com.example.appdist;

import java.io.*;
import java.sql.*;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.Models.User;
import com.example.appdist.Models.UserDAO.UserDAO;
import com.example.appdist.Models.UserDAO.UserDAOImpl;
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
            if (password.length() < 6) {
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

            User user = new User(email, password, name, phone, "user");
            UserDAO userDAO = new UserDAOImpl();

            int result = userDAO.insert(user);
            if (result == 400) {
                String errorMessage = "Email already exists";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }

            if (result == 500) {
                request.setAttribute("status", "failed");
                request.setAttribute("message", "Something went wrong!");
                response.sendRedirect(request.getContextPath() + "/SignUp");
            }

            request.setAttribute("status", "success");
            request.setAttribute("message", "You have successfully registered!");
            response.sendRedirect(request.getContextPath() + "/Login");

        }catch (Exception err){
            err.printStackTrace();
        }
    }
}