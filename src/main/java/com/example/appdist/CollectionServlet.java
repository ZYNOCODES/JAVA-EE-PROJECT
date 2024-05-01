package com.example.appdist;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.Models.Collection;
import com.example.appdist.Models.Post;
import com.example.appdist.Models.PostDAO.PostDAO;
import com.example.appdist.Models.PostDAO.PostDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CollectionServlet", value = "/Collection")
public class CollectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";

        switch (action){
            case "delete":
                try {
                    // Get the id of the record to be deleted
                    int idToDelete = Integer.parseInt(req.getParameter("postID"));
                    PostDAO postDAO = new PostDAOImpl();
                    Post post = new Post(idToDelete);

                    int result = postDAO.delete(post);
                    if (result == 200){
                        req.setAttribute("message", "Post with ID " + idToDelete + " deleted successfully!");
                        resp.sendRedirect(req.getContextPath() + "/Home");
                    }

                    if (result == 400) {
                        String errorMessage = "Post with ID " + idToDelete + " not found";
                        req.setAttribute("errorMessage", errorMessage);
                        req.getRequestDispatcher("index.jsp").forward(req, resp);
                        return;
                    }

                    String errorMessage = "Failed to delete post with ID " + idToDelete;
                    req.setAttribute("errorMessage", errorMessage);
                    req.getRequestDispatcher("index.jsp").forward(req, resp);

                } catch (Exception err) {
                    err.printStackTrace();
                }
                break;
            default:
                int cardID = Integer.parseInt(req.getParameter("cardID"));
                try {

                    Collection collection = new Collection(cardID);
                    PostDAO postDAO = new PostDAOImpl();
                    List<Post> posts = postDAO.getAllbyCollection(collection);

                    if (posts.isEmpty())
                        req.setAttribute("error", "No posts found.");
                    else
                        req.setAttribute("posts", posts);

                    RequestDispatcher dispatcher = req.getRequestDispatcher("collection.jsp");
                    dispatcher.forward(req, resp);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}