package com.example.appdist;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.appdist.Config.DBConfiguration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "HomeServlet", value = "/Home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        String SELECTquery = "SELECT * FROM collection";
        try {
            connection = DBConfiguration.getConnection();

            PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
            ResultSet SELECTresultSet = SELECTStatement.executeQuery();

            List<com.example.appdist.Models.Collection> items = new ArrayList<>();
            while (SELECTresultSet.next()) {
                com.example.appdist.Models.Collection collection = new com.example.appdist.Models.Collection(
                        SELECTresultSet.getInt("id"),
                        SELECTresultSet.getString("name"),
                        SELECTresultSet.getString("description"),
                        SELECTresultSet.getString("end_date")
                );
                items.add(collection);
            }
            if (items.isEmpty())
                req.setAttribute("error", "No collections found.");
            else
                req.setAttribute("cards", items);

            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}