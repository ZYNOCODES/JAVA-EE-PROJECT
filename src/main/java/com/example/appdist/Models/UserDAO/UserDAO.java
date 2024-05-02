package com.example.appdist.Models.UserDAO;

import com.example.appdist.interfaces.DAO;
import com.example.appdist.Models.User;

import java.sql.SQLException;

public interface UserDAO extends DAO<User> {
    User login(User user) throws SQLException;

}
