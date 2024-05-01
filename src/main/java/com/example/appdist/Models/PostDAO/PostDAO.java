package com.example.appdist.Models.PostDAO;

import com.example.appdist.Models.Collection;
import com.example.appdist.Models.DAO;
import com.example.appdist.Models.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostDAO extends DAO<Post> {
    List<Post> getAllbyCollection(Collection collection) throws SQLException;

}
