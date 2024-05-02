package com.example.appdist;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.Models.Collection;
import com.example.appdist.Models.Post;
import com.example.appdist.Models.PostDAO.PostDAO;
import com.example.appdist.Models.PostDAO.PostDAOImpl;
import com.example.appdist.Models.User;
import com.example.appdist.Models.UserDAO.UserDAO;
import com.example.appdist.Models.UserDAO.UserDAOImpl;
import com.example.appdist.Models.Vote;
import com.example.appdist.Models.VoteDAO.VoteDAO;
import com.example.appdist.Models.VoteDAO.VoteDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.print.attribute.DateTimeSyntax;

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
                    List<Post> posts = postDAO.getAllbyCollection(collection, 13);

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String post = request.getParameter("post");
            String collection = request.getParameter("collection");

            if (collection.isEmpty() || post.isEmpty()) {
                String errorMessage = "Please fill all the required fields";
                request.setAttribute("errorMessage", errorMessage);
                System.out.println(errorMessage);
                request.getRequestDispatcher("collection.jsp").forward(request, response);
                return;
            }
            LocalDateTime currentDateTime = LocalDateTime.now();
            Vote vote = new Vote(Integer.parseInt(post), Integer.parseInt("13"), currentDateTime.toString());
            VoteDAO voteDAO = new VoteDAOImpl();
            int result = voteDAO.SubmitVote(vote, Integer.parseInt(collection));
            if (result == 400) {
                String errorMessage = "you already voted for a post in this collection";
                System.out.println(errorMessage);
                request.setAttribute("errorMessage", errorMessage);
                response.sendRedirect(request.getContextPath() + "/Collection?cardID="+collection);
                return;
            }
            if (result == 200) {
                request.setAttribute("message", "You have successfully voted!");
                System.out.println("You have successfully voted!");
                response.sendRedirect(request.getContextPath() + "/Collection?cardID="+collection);
                return;
            }
            String errorMessage = "Something went wrong!";
            request.setAttribute("errorMessage", errorMessage);
            System.out.println(errorMessage);
            request.getRequestDispatcher("collection.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}