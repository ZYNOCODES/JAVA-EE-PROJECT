package com.example.appdist;

import java.io.*;
import java.util.List;

import com.example.appdist.Models.Collection;
import com.example.appdist.Models.CollectionDAO.CollectionDAO;
import com.example.appdist.Models.CollectionDAO.CollectionDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "HomeServlet", value = "/Home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CollectionDAO collectionDAO = new CollectionDAOImpl();
            List<Collection> items = collectionDAO.getAll();
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