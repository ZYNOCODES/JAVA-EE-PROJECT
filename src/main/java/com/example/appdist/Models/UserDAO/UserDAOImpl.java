package com.example.appdist.Models.UserDAO;

import com.example.appdist.Config.DBConfiguration;
import com.example.appdist.Models.User;
import com.example.appdist.util.PasswordHashing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    @Override
    public User get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public int insert(User user) throws SQLException {
        Connection connection = null;
        String SELECTquery = "SELECT * FROM users WHERE email = ?";
        String INSERTquery = "insert into users(name, email, password, phone, type) values(?,?,?,?,?)";

        connection = DBConfiguration.getConnection();

        PreparedStatement SELECTStatement = connection.prepareStatement(SELECTquery);
        SELECTStatement.setString(1, user.getEmail());
        ResultSet SELECTresultSet = SELECTStatement.executeQuery();
        if (SELECTresultSet.next()) {
            return 400;
        }

        PreparedStatement INSERTStatement = connection.prepareStatement(INSERTquery);
        INSERTStatement.setString(1, user.getName());
        INSERTStatement.setString(2, user.getEmail());
        String hash = PasswordHashing.hashPassword(user.getPassword());
        INSERTStatement.setString(3, hash);
        INSERTStatement.setString(4, user.getPhone());
        INSERTStatement.setString(5, user.getType());


        int rowCount = INSERTStatement.executeUpdate();
        if (rowCount <= 0)
            return 500;
        else
            return 200;
    }

    @Override
    public int update(User user) throws SQLException {
        return 0;
    }

    @Override
    public int delete(User user) throws SQLException {
        return 0;
    }

    @Override
    public User login(User user) throws SQLException {
        Connection connection = null;
        String query = "SELECT * FROM users WHERE email = ?";
        connection = DBConfiguration.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getEmail());

        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            return null;
        }

        boolean isMatch = PasswordHashing.verifyPassword(user.getPassword(), resultSet.getString("password"));
        if (!isMatch) {
            return null;
        }

        User data = new User(
                resultSet.getInt("id"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getString("phone"),
                resultSet.getString("type")
        );

        return data;
    }
}
