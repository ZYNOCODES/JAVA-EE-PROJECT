package com.example.appdist;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        Connection connection = null;
        String SELECTquery = "SELECT * FROM `post` WHERE collection = ?";
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
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}