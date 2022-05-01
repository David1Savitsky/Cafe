package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Comment;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.exception.ServiceException;
import javafx.util.Pair;

import java.util.List;

/**
 * This interface declares the methods that will interact with database.
 *
 * @author David Savitsky
 * @version 1.0
 * @since 1.0
 */
public interface CommentDao extends Dao<Comment> {

    /**
     * This method add user's comment to the food item.
     *
     * @param foodId food's id.
     * @param userId user's id.
     * @param comment user's comment.
     * @throws DaoException if any dao exception occurred during processing.
     */
    void insertComment(Long foodId, Long userId, String comment) throws DaoException;

    /**
     * This method gets list of user's comments of the food item.
     *
     * @param foodId food's id.
     * @return {@code List<Pair<User, Comment>>} list of user's comments.
     * @throws DaoException if any dao exception occurred during processing.
     */
    List<Pair<User, Comment>> getComments(Long foodId) throws DaoException;
}
