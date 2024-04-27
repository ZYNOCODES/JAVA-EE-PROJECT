package com.example.appdist;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.Models.Collection;
import com.example.appdist.util.PasswordHashing;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddNewPostServlet", value = "/AddNewPost")
public class AddNewPostServlet extends HttpServlet {
    List<Collection> items = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        String SELECTquery = "SELECT * FROM collection";
        try {
            connection = DBConfiguration.getConnection();

            PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
            ResultSet SELECTresultSet = SELECTStatement.executeQuery();

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

            RequestDispatcher dispatcher = req.getRequestDispatcher("newPostForm.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        String SELECTquery = "SELECT * FROM post WHERE title = ?";
        String INSERTquery = "insert into post(collection, title, description, image) values(?,?,?,?)";
        try {
            String collection = req.getParameter("collection");
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String image = req.getParameter("image");

            if (collection.isEmpty() || title.isEmpty() || description.isEmpty() || image.isEmpty()) {
                String errorMessage = "Please fill all the required fields";
                req.setAttribute("errorMessage", errorMessage);
                req.setAttribute("cards", items);
                req.getRequestDispatcher("newPostForm.jsp").forward(req, resp);
                return;
            }

            connection = DBConfiguration.getConnection();

            PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
            SELECTStatement.setString(1, title);
            ResultSet SELECTresultSet = SELECTStatement.executeQuery();
            if (SELECTresultSet.next()) {
                String errorMessage = "A post with this title already exists";
                req.setAttribute("errorMessage", errorMessage);
                req.setAttribute("cards", items);
                req.getRequestDispatcher("newPostForm.jsp").forward(req, resp);
                return;
            }

            PreparedStatement INSERTStatement = connection.prepareStatement(INSERTquery);
            INSERTStatement.setString(1, collection);
            INSERTStatement.setString(2, title);
            INSERTStatement.setString(3, description);
            INSERTStatement.setString(4, image);

            int rowCount = INSERTStatement.executeUpdate();
            if (rowCount <= 0) {
                String errorMessage = "Something went wrong!";
                req.setAttribute("errorMessage", errorMessage);
                req.setAttribute("cards", items);
                req.getRequestDispatcher("newPostForm.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("message", "You have successfully add a post!");
            resp.sendRedirect(req.getContextPath() + "/Collection?cardID="+collection);

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
}