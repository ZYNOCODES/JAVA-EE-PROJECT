package com.example.appdist;

import com.example.appdist.Models.Collection;
import com.example.appdist.Models.Post;
import com.example.appdist.Models.PostDAO.PostDAO;
import com.example.appdist.Models.PostDAO.PostDAOImpl;
import com.example.appdist.Models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "VotingResultServlet", value = "/VotingResult")
public class VotingResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cardID = Integer.parseInt(req.getParameter("cardID"));
        try {
            Collection collection = new Collection(cardID);
            PostDAO postDAO = new PostDAOImpl();
            List<Post> posts = postDAO.getAllVotingResultbyCollection(collection);

            if (posts.size() <= 0)
                req.setAttribute("error", "No posts found.");
            else
                req.setAttribute("posts", posts);

            RequestDispatcher dispatcher = req.getRequestDispatcher("votingResult.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
