package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Comment;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.mapper.CommentRowMapper;
import com.epam.webappfinal.mapper.UserRowMapper;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommentDaoImpl extends AbstractDao<Comment> implements CommentDao {

    private static final String INSERT_COMMENT_QUERY = "insert into comments set comment = ?, food_id = ?, user_id = ? ; ";
    private static final String GET_COMMENTS_QUERY =
            "SELECT u.*, c.* \n" +
            "FROM comments c INNER JOIN users u\n" +
                "ON u.id = c.user_id\n" +
            "WHERE food_id = %d\n" +
            "ORDER BY c.id DESC;";

    private final Connection connection;

    public CommentDaoImpl(Connection connection) {
        super(connection, new CommentRowMapper(), Comment.TABLE_NAME);
        this.connection = connection;
    }

    @Override
    protected Map<String, Object> getFields(Comment item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(Comment.ID, item.getId());
        fields.put(Comment.COMMENT, item.getComment());
        fields.put(Comment.USER_ID, item.getUserId());
        fields.put(Comment.FOOD_ID, item.getFoodId());
        return fields;
    }

    @Override
    public void insertComment(Long foodId, Long userId, String comment) throws DaoException {
        executeUpdate(INSERT_COMMENT_QUERY, comment, foodId, userId);
    }

    @Override
    public List<Pair<User, Comment>> getComments(Long foodId) throws DaoException {
        String query = String.format(GET_COMMENTS_QUERY, foodId);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            return extractCommentsFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private List<Pair<User, Comment>> extractCommentsFromResultSet(ResultSet resultSet) throws SQLException {
        UserRowMapper userRowMapper = new UserRowMapper();
        CommentRowMapper commentRowMapper = new CommentRowMapper();
        List<Pair<User, Comment>> commentList = new ArrayList<>();
        while (resultSet.next()) {
            User user = userRowMapper.map(resultSet);
            Comment comment = commentRowMapper.map(resultSet);
            comment.setId(resultSet.getLong("c.id"));
            commentList.add(new Pair<>(user, comment));
        }
        return commentList;
    }
}
