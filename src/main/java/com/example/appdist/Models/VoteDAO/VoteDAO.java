package com.example.appdist.Models.VoteDAO;

import com.example.appdist.Models.Collection;
import com.example.appdist.Models.Post;
import com.example.appdist.interfaces.DAO;
import com.example.appdist.Models.Vote;

import java.sql.SQLException;
import java.util.List;

public interface VoteDAO extends DAO<Vote> {
    List<Vote> getAllbyCollection(Collection collection) throws SQLException;
    int SubmitVote(Vote vote, int id) throws SQLException;

}
