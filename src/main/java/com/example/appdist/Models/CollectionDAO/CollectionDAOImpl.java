package com.example.appdist.Models.CollectionDAO;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.Models.Collection;
import jakarta.servlet.RequestDispatcher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollectionDAOImpl implements CollectionDAO{
    @Override
    public Collection get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Collection> getAll() throws SQLException {
        Connection connection = null;
        String SELECTquery = "SELECT * FROM collection";
        connection = DBConfiguration.getConnection();

        PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
        ResultSet SELECTresultSet = SELECTStatement.executeQuery();

        List<Collection> items = new ArrayList<>();
        while (SELECTresultSet.next()) {
            Collection collection = new Collection(
                    SELECTresultSet.getInt("id"),
                    SELECTresultSet.getString("name"),
                    SELECTresultSet.getString("description"),
                    SELECTresultSet.getString("end_date"),
                    SELECTresultSet.getString("image")

            );
            items.add(collection);
        }
        return items;
    }



    @Override
    public int insert(Collection collection) throws SQLException {
        Connection connection = null;
        String SELECTquery = "SELECT * FROM collection WHERE name = ?";
        String INSERTquery = "insert into collection(name, description, end_date, image) values(?,?,?,?)";

        connection = DBConfiguration.getConnection();

        PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
        SELECTStatement.setString(1, collection.getName());
        ResultSet SELECTresultSet = SELECTStatement.executeQuery();
        if (SELECTresultSet.next()) {
            return 400;
        }

        PreparedStatement INSERTStatement = connection.prepareStatement(INSERTquery);
        INSERTStatement.setString(1, collection.getName());
        INSERTStatement.setString(2, collection.getDescription());
        INSERTStatement.setString(3, collection.getEnd_date());
        INSERTStatement.setString(4, collection.getImg());

        int rowCount = INSERTStatement.executeUpdate();
        if (rowCount > 0)
            return 200;
        else
            return 500;
    }

    @Override
    public int update(Collection collection) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Collection collection) throws SQLException{
        Connection connection = null;
        String DELETEquery = "DELETE FROM collection WHERE id = ?";
        String DELETEPostsQuery = "DELETE FROM post WHERE collection = ?";
        String SELECTquery = "SELECT * FROM collection WHERE id = ?";

        connection = DBConfiguration.getConnection();

        // Check if the record exists
        PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
        SELECTStatement.setInt(1, collection.getId());
        ResultSet SELECTresultSet = SELECTStatement.executeQuery();
        if (!SELECTresultSet.next()) {
            return 400;
        }
        // Execute the delete query
        PreparedStatement DELETEPostsStatement = connection.prepareStatement(DELETEPostsQuery);
        DELETEPostsStatement.setInt(1, collection.getId());
        int rowcountPosts = DELETEPostsStatement.executeUpdate();
        if (rowcountPosts <= 0) {
            return 500;
        }
        // Execute the delete query
        PreparedStatement DELETEStatement = connection.prepareStatement(DELETEquery);
        DELETEStatement.setInt(1, collection.getId());

        int rowcount = DELETEStatement.executeUpdate();
        if (rowcount > 0)
            return 200;
        else
            return 500;
    }
}
