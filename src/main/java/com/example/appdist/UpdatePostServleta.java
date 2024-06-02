package com.example.appdist;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UpdatePostServleta", value = "/UpdatePost")
public class UpdatePostServleta extends HttpServlet {
    List<Collection> items = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CollectionDAO collectionDAO = new CollectionDAOImpl();
            items = collectionDAO.getAll();

            String postID = req.getParameter("postID");
            PostDAO postDAO = new PostDAOImpl();
            Post post = postDAO.get(Integer.parseInt(postID));
            if (post.getTitle().isEmpty())
                req.setAttribute("error", "No post data found.");
            else
                req.setAttribute("post", post);

            if (items.isEmpty())
                req.setAttribute("error", "No collections found.");
            else
                req.setAttribute("cards", items);

            RequestDispatcher dispatcher = req.getRequestDispatcher("UpdatePostForm.jsp");
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
            String postID = req.getParameter("postID");

            if (postID.isEmpty() || collection.isEmpty() && title.isEmpty() && description.isEmpty() && image.isEmpty()) {
                String errorMessage = "Please do at least one modification";
                System.out.println(errorMessage);
                req.setAttribute("errorMessage", errorMessage);
                req.setAttribute("cards", items);
                req.getRequestDispatcher("newPostForm.jsp").forward(req, resp);
                return;
            }

            Post post = new Post(Integer.parseInt(postID) ,Integer.parseInt(collection), title, description, image);
            PostDAO postDAO = new PostDAOImpl();
            int result = postDAO.update(post);
            if (result == 200) {
                req.setAttribute("message", "You have successfully update a post!");
                resp.sendRedirect(req.getContextPath() + "/Post?postID="+postID);
                return;
            }

            if (result == 400) {
                String errorMessage = "A post with this id doesnt exists";
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
