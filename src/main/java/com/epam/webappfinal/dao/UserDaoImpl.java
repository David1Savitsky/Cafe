package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.mapper.UserRowMapper;

import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public static final String FIND_BY_LOGIN_AND_PASSWORD = "select * from user where login = ? and password = MD5(?) ;";

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
    protected Map<String, Object> getFields(User item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(User.NAME, item.getName());
        //...
        return fields;
    }


}
