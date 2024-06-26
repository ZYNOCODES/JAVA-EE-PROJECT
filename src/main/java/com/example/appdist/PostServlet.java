package com.example.appdist;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.Models.Post;
import com.example.appdist.Models.PostDAO.PostDAO;
import com.example.appdist.Models.PostDAO.PostDAOImpl;
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


@WebServlet(name = "PostServlet", value = "/Post")
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String postID = req.getParameter("postID");
            PostDAO postDAO = new PostDAOImpl();
            Post post = postDAO.get(Integer.parseInt(postID));

            if (post.getTitle().isEmpty()) {
                req.setAttribute("error", "No data found.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("post.jsp");
                dispatcher.forward(req, resp);
                return;
            }

            req.setAttribute("post", post);
            RequestDispatcher dispatcher = req.getRequestDispatcher("post.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}