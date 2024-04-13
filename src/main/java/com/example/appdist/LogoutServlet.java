package com.example.appdist;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "LogoutServlet", value = "/Logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("token");
            response.setHeader("Clear-Site-Data", "\"session-storage\"");
            response.sendRedirect(request.getContextPath() + "/Login");
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/Login");
        }
    }
}