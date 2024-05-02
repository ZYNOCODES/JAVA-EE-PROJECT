package com.example.appdist;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.Models.Collection;
import com.example.appdist.Models.CollectionDAO.CollectionDAO;
import com.example.appdist.Models.CollectionDAO.CollectionDAOImpl;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddNewPostServlet", value = "/AddNewPost")
public class AddNewPostServlet extends HttpServlet {
    List<Collection> items = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CollectionDAO collectionDAO = new CollectionDAOImpl();
            List<Collection> items = collectionDAO.getAll();
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

            Post post = new Post(Integer.parseInt(collection), title, description, image);
            PostDAO postDAO = new PostDAOImpl();
            int result = postDAO.insert(post);
            if (result == 200) {
                req.setAttribute("message", "You have successfully add a post!");
                resp.sendRedirect(req.getContextPath() + "/Collection?cardID="+collection);
                return;
            }

            if (result == 400) {
                String errorMessage = "A post with this title already exists";
                req.setAttribute("errorMessage", errorMessage);
                req.setAttribute("cards", items);
                req.getRequestDispatcher("newPostForm.jsp").forward(req, resp);
                return;
            }

            String errorMessage = "Something went wrong!";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("newPostForm.jsp").forward(req, resp);

        }catch (Exception err){
            err.printStackTrace();
        }
    }
}