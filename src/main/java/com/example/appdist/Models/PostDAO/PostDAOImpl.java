package com.example.appdist.Models.PostDAO;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.Models.Collection;
import com.example.appdist.Models.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl implements PostDAO{

    @Override
    public Post get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Post> getAll() throws SQLException {
        return null;
    }

    @Override
    public int insert(Post post) throws SQLException {
        Connection connection = null;
        String SELECTquery = "SELECT * FROM post WHERE title = ?";
        String INSERTquery = "insert into post(collection, title, description, image) values(?,?,?,?)";
        connection = DBConfiguration.getConnection();

        PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
        SELECTStatement.setString(1, post.getTitle());
        ResultSet SELECTresultSet = SELECTStatement.executeQuery();
        if (SELECTresultSet.next()) {
            return 400;
        }

        PreparedStatement INSERTStatement = connection.prepareStatement(INSERTquery);
        INSERTStatement.setInt(1, post.getCollection());
        INSERTStatement.setString(2, post.getTitle());
        INSERTStatement.setString(3, post.getDescription());
        INSERTStatement.setString(4, post.getImage());

        int rowCount = INSERTStatement.executeUpdate();
        if (rowCount <= 0)
            return 500;
        else
            return 200;
    }

    @Override
    public int update(Post post) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Post post) throws SQLException {
        Connection connection = null;
        String DELETEquery = "DELETE FROM post WHERE id = ?";
        String SELECTquery = "SELECT * FROM post WHERE id = ?";

        connection = DBConfiguration.getConnection();

        // Check if the record exists
        PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
        SELECTStatement.setInt(1, post.getId());
        ResultSet SELECTresultSet = SELECTStatement.executeQuery();
        if (!SELECTresultSet.next()) {
            return 400;
        }
        // Execute the delete query
        PreparedStatement DELETEStatement = connection.prepareStatement(DELETEquery);
        DELETEStatement.setInt(1, post.getId());

        int rowcount = DELETEStatement.executeUpdate();
        if (rowcount > 0)
            return 200;
        else
            return 500;
    }

    @Override
    public List<Post> getAllbyCollection(Collection collection) throws SQLException {
        Connection connection = DBConfiguration.getConnection();
        String SELECTquery = "SELECT * FROM `post` WHERE collection = ?";

        PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
        SELECTStatement.setInt(1, collection.getId());
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
        return posts;
    }
}
