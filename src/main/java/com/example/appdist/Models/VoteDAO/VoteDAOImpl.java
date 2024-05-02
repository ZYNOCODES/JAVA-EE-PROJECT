package com.example.appdist.Models.VoteDAO;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.Models.Collection;
import com.example.appdist.Models.Post;
import com.example.appdist.Models.Vote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoteDAOImpl implements VoteDAO{
    @Override
    public Vote get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Vote> getAll() throws SQLException {
        return null;
    }

    @Override
    public int insert(Vote vote) throws SQLException {
        return 0;
    }

    @Override
    public int update(Vote vote) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Vote vote) throws SQLException {
        Connection connection = null;
        String DELETEquery = "DELETE FROM vote WHERE user = ? AND post = ?";
        String SELECTquery = "SELECT * FROM post WHERE user = ? AND post = ?";

        connection = DBConfiguration.getConnection();

        // Check if the record exists
        PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
        SELECTStatement.setInt(1, vote.getUser());
        SELECTStatement.setInt(2, vote.getPost());

        ResultSet SELECTresultSet = SELECTStatement.executeQuery();
        if (!SELECTresultSet.next()) {
            return 400;
        }
        // Execute the delete query
        PreparedStatement DELETEStatement = connection.prepareStatement(DELETEquery);
        DELETEStatement.setInt(1, vote.getUser());
        DELETEStatement.setInt(2, vote.getPost());

        int rowcount = DELETEStatement.executeUpdate();
        if (rowcount > 0)
            return 200;
        else
            return 500;
    }

    @Override
    public List<Vote> getAllbyCollection(Collection collection) throws SQLException {
        return null;
    }

    @Override
    public int SubmitVote(Vote vote, int id) throws SQLException {
        Connection connection = null;
        String SELECTquery = "SELECT * FROM vote WHERE user = ? AND post = ?";
        String SELECTPostsquery = "SELECT * FROM post WHERE collection = ?";
        String INSERTquery = "insert into vote(user, post, date) values(?,?,?)";

        connection = DBConfiguration.getConnection();

        PreparedStatement SELECTPostsStatement = connection.prepareStatement(SELECTPostsquery);
        SELECTPostsStatement.setInt(1, id);
        ResultSet SELECTPostdresultSet = SELECTPostsStatement.executeQuery();
        List<Post> posts = new ArrayList<>();
        while (SELECTPostdresultSet.next()){
            Post post = new Post(
                    SELECTPostdresultSet.getInt("id"),
                    SELECTPostdresultSet.getInt("collection"),
                    SELECTPostdresultSet.getString("title"),
                    SELECTPostdresultSet.getString("description"),
                    SELECTPostdresultSet.getString("image")
            );
            posts.add(post);
        }
        for (int i = 0; i < posts.size(); i++){
            PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
            SELECTStatement.setInt(1, vote.getUser());
            SELECTStatement.setInt(2, posts.get(i).getId());
            ResultSet SELECTresultSet = SELECTStatement.executeQuery();
            if (SELECTresultSet.next()) {
                return 400;
            }
        }
        PreparedStatement INSERTStatement = connection.prepareStatement(INSERTquery);
        INSERTStatement.setInt(1, vote.getUser());
        INSERTStatement.setInt(2, vote.getPost());
        INSERTStatement.setString(3, vote.getDate());

        int rowCount = INSERTStatement.executeUpdate();
        if (rowCount <= 0)
            return 500;
        else
            return 200;
    }
}
