package com.example.appdist;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.Models.Collection;
import com.example.appdist.Models.CollectionDAO.CollectionDAO;
import com.example.appdist.Models.CollectionDAO.CollectionDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "AddNewCollectionServlet", value = "/AddNewCollection")
public class AddNewCollectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";
        switch (action){
            case "delete":
                try {
                    // Get the id of the record to be deleted
                    int idToDelete = Integer.parseInt(req.getParameter("collection"));
                    Collection collection = new Collection(idToDelete);
                    CollectionDAO collectionDAO = new CollectionDAOImpl();

                    int result = collectionDAO.delete(collection);
                    if (result == 200){
                        req.setAttribute("message", "Collection with ID " + idToDelete + " deleted successfully!");
                        resp.sendRedirect(req.getContextPath() + "/Home");
                        return;
                    }

                    if (result == 400) {
                        String errorMessage = "Collection with ID " + idToDelete + " not found";
                        req.setAttribute("errorMessage", errorMessage);
                        req.getRequestDispatcher("index.jsp").forward(req, resp);
                        return;
                    }

                    String errorMessage = "Failed to delete collection with ID " + idToDelete;
                    req.setAttribute("errorMessage", errorMessage);
                    req.getRequestDispatcher("index.jsp").forward(req, resp);

                } catch (Exception err) {
                    err.printStackTrace();
                }
                break;
            default:
                RequestDispatcher dispatcher = req.getRequestDispatcher("newCollectionForm.jsp");
                dispatcher.forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String end_date = req.getParameter("end_date");
            String description = req.getParameter("description");
            String image = req.getParameter("imageUrl");

            if (name.isEmpty() || end_date.isEmpty() || description.isEmpty() || image.isEmpty()) {
                String errorMessage = "Please fill all the required fields";
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("newCollectionForm.jsp").forward(req, resp);
                return;
            }
            Collection collection = new Collection(name, description, end_date, image);
            CollectionDAO collectionDAO = new CollectionDAOImpl();
            int result = collectionDAO.insert(collection);
            if (result == 400) {
                String errorMessage = "A collection with this name already exists";
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("newCollectionForm.jsp").forward(req, resp);
                return;
            }

            if (result == 200) {
                req.setAttribute("message", "You have successfully add a collection!");
                resp.sendRedirect(req.getContextPath() + "/Home");
                return;
            }
            String errorMessage = "Something went wrong!";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("newCollectionForm.jsp").forward(req, resp);
        }catch (Exception err){
            err.printStackTrace();
        }
    }
}