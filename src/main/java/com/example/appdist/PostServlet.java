package com.example.appdist;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.Models.Post;
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
        Connection connection = null;
        String SELECTquery = "SELECT * FROM `post` WHERE id = ?";
        String postID = req.getParameter("postID");

        try {
            connection = DBConfiguration.getConnection();

            PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
            SELECTStatement.setString(1, postID);
            ResultSet SELECTresultSet = SELECTStatement.executeQuery();

            if (!SELECTresultSet.next()) {
                req.setAttribute("error", "No data found.");
                return;
            }

            Post post = new Post(
                    SELECTresultSet.getInt("id"),
                    SELECTresultSet.getInt("collection"),
                    SELECTresultSet.getString("title"),
                    SELECTresultSet.getString("description"),
                    SELECTresultSet.getString("image")
            );

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