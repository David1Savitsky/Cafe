package com.epam.webappfinal.mapper;

import com.epam.webappfinal.entity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Comment.ID);
        String comment = resultSet.getString(Comment.COMMENT);
        Long userId = resultSet.getLong(Comment.USER_ID);
        Long foodId = resultSet.getLong(Comment.FOOD_ID);

        return new Comment(id, comment, userId, foodId);
    }
}
