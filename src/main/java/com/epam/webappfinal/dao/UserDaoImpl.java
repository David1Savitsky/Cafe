package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.mapper.UserRowMapper;

import java.sql.Connection;
import java.util.*;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "select * from users where login = ? and password = MD5(?) ;";
    private static final String GET_ALL_USERS_QUERY = "select * from users";
    private static final String UPDATE_LOYALTY_POINTS_QUERY = "update users set loyalty_points = ? where id = ?; ";
    private static final String INSERT_NEW_USER = "insert into users set name = ?, surname = ?, login = ?, password = MD5(?); ";
//    private static final String INSERT_NEW_USER = "insert into users (name, surname, login, password) values (%s, %s, %s, MD5(%s)); ";

    public UserDaoImpl(Connection connection) {
        super(connection, new UserRowMapper(), User.TABLE_NAME);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(
                FIND_BY_LOGIN_AND_PASSWORD,
                login,
                password);
    }

    @Override
    public void register(String name, String surname, String login, String password) throws DaoException {
//        String query = String.format(INSERT_NEW_USER, name, surname, login, password);
        executeUpdate(INSERT_NEW_USER, name, surname, login, password);
    }

    @Override
    public List<User> getUsers() throws DaoException {
        return executeQuery(GET_ALL_USERS_QUERY);
    }

    @Override
    public void changeLoyaltyPoints(Long id, Integer loyaltyPoints) throws DaoException {
        executeUpdate(UPDATE_LOYALTY_POINTS_QUERY, loyaltyPoints, id);
    }

    @Override
    protected Map<String, Object> getFields(User item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(User.ID, item.getId());
        fields.put(User.NAME, item.getName());
        fields.put(User.SURNAME, item.getSurname());
        fields.put((User.LOGIN), item.getLogin());
        fields.put((User.IS_ADMIN), item.isAdmin());
        fields.put((User.AMOUNT), item.getAmount());
        fields.put((User.LOYALTY_POINTS), item.getLoyaltyPoints());
        fields.put((User.IS_BLOCKED), item.isBlocked());
        return fields;
    }
}
