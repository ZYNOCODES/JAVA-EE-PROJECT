package com.example.appdist;

import com.example.appdist.Config.DBConfiguration;
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
                Connection connection = null;
                String DELETEquery = "DELETE FROM collection WHERE id = ?";
                String SELECTquery = "SELECT * FROM collection WHERE id = ?";
                try {
                    // Get the id of the record to be deleted
                    int idToDelete = Integer.parseInt(req.getParameter("collection"));

                    connection = DBConfiguration.getConnection();

                    // Check if the record exists
                    PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
                    SELECTStatement.setInt(1, idToDelete);
                    ResultSet SELECTresultSet = SELECTStatement.executeQuery();
                    if (!SELECTresultSet.next()) {
                        String errorMessage = "Collection with ID " + idToDelete + " not found";
                        req.setAttribute("errorMessage", errorMessage);
                        req.getRequestDispatcher("index.jsp").forward(req, resp);
                        return;
                    }

                    // Execute the delete query
                    PreparedStatement DELETEStatement = connection.prepareStatement(DELETEquery);
                    DELETEStatement.setInt(1, idToDelete);
                    int rowCount = DELETEStatement.executeUpdate();
                    if (rowCount <= 0) {
                        String errorMessage = "Failed to delete collection with ID " + idToDelete;
                        req.setAttribute("errorMessage", errorMessage);
                        req.getRequestDispatcher("index.jsp").forward(req, resp);
                        return;
                    }
                    req.setAttribute("message", "Collection with ID " + idToDelete + " deleted successfully!");
                    resp.sendRedirect(req.getContextPath() + "/Home");

                } catch (Exception err) {
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
                break;
            default:
                RequestDispatcher dispatcher = req.getRequestDispatcher("newCollectionForm.jsp");
                dispatcher.forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        String SELECTquery = "SELECT * FROM collection WHERE name = ?";
        String INSERTquery = "insert into collection(name, description, end_date, image) values(?,?,?,?)";
        try {
            String name = req.getParameter("name");
            String end_date = req.getParameter("end_date");
            String description = req.getParameter("description");
            String image = req.getParameter("image");

            if (name.isEmpty() || end_date.isEmpty() || description.isEmpty() || image.isEmpty()) {
                String errorMessage = "Please fill all the required fields";
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("newCollectionForm.jsp").forward(req, resp);
                return;
            }

            connection = DBConfiguration.getConnection();

            PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
            SELECTStatement.setString(1, name);
            ResultSet SELECTresultSet = SELECTStatement.executeQuery();
            if (SELECTresultSet.next()) {
                String errorMessage = "A collection with this name already exists";
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("newCollectionForm.jsp").forward(req, resp);
                return;
            }

            PreparedStatement INSERTStatement = connection.prepareStatement(INSERTquery);
            INSERTStatement.setString(1, name);
            INSERTStatement.setString(2, description);
            INSERTStatement.setString(3, end_date);
            INSERTStatement.setString(4, image);

            int rowCount = INSERTStatement.executeUpdate();
            if (rowCount <= 0) {
                String errorMessage = "Something went wrong!";
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("newCollectionForm.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("message", "You have successfully add a collection!");
            resp.sendRedirect(req.getContextPath() + "/Home");

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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}