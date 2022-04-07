package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Comment;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;
import javafx.util.Pair;

import java.util.List;

public interface CommentDao extends Dao<Comment> {

    void insertComment(Long foodId, Long userId, String comment) throws DaoException;

    List<Pair<User, Comment>> getComments(Long foodId) throws DaoException;

}
