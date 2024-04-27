package com.example.appdist;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.Models.Post;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CollectionServlet", value = "/Collection")
public class CollectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";
        Connection connection = null;
        String DELETEquery = "DELETE FROM post WHERE id = ?";
        String SELECTQuery = "SELECT * FROM post WHERE id = ?";
        String SELECTquery = "SELECT * FROM `post` WHERE collection = ?";

        switch (action){
            case "delete":
                try {
                    // Get the id of the record to be deleted
                    int idToDelete = Integer.parseInt(req.getParameter("postID"));

                    connection = DBConfiguration.getConnection();

                    // Check if the record exists
                    PreparedStatement SELECTStatement = connection.prepareStatement(SELECTQuery);
                    SELECTStatement.setInt(1, idToDelete);
                    ResultSet SELECTresultSet = SELECTStatement.executeQuery();
                    if (!SELECTresultSet.next()) {
                        String errorMessage = "Post with ID " + idToDelete + " not found";
                        req.setAttribute("errorMessage", errorMessage);
                        req.getRequestDispatcher("index.jsp").forward(req, resp);
                        return;
                    }

                    // Execute the delete query
                    PreparedStatement DELETEStatement = connection.prepareStatement(DELETEquery);
                    DELETEStatement.setInt(1, idToDelete);
                    int rowCount = DELETEStatement.executeUpdate();
                    if (rowCount <= 0) {
                        String errorMessage = "Failed to delete post with ID " + idToDelete;
                        req.setAttribute("errorMessage", errorMessage);
                        req.getRequestDispatcher("index.jsp").forward(req, resp);
                        return;
                    }
                    req.setAttribute("message", "Post with ID " + idToDelete + " deleted successfully!");
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
                String cardID = req.getParameter("cardID");
                try {
                    connection = DBConfiguration.getConnection();

                    PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
                    SELECTStatement.setString(1, cardID);
                    ResultSet SELECTresultSet = SELECTStatement.executeQuery();

                    List<Post> posts = new ArrayList<>();
                    while (SELECTresultSet.next()) {
                        Post post = new Post(
                                SELECTresultSet.getInt("id"),
                                SELECTresultSet.getInt("collection"),
                                SELECTresultSet.getString("title"),
                                SELECTresultSet.getString("description"),
                                SELECTresultSet.getString("image")
                        );
                        posts.add(post);
                    }
                    if (posts.size() <= 0)
                        req.setAttribute("error", "No posts found.");
                    else
                        req.setAttribute("posts", posts);

                    RequestDispatcher dispatcher = req.getRequestDispatcher("collection.jsp");
                    dispatcher.forward(req, resp);

                } catch (Exception e) {
                    e.printStackTrace();
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
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}