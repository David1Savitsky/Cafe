package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;

import java.sql.Connection;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public static final String FIND_BY_LOGIN_AND_PASSWORD = "select * from user where login = ? and password = ?";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(
                FIND_BY_LOGIN_AND_PASSWORD,
                new UserRowMapper(),
                login,
                password);
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.empty();
        // Specification spec = new FindByIdSpecification;
        // return executeQuery("select * from user where" spec.toSql(), new UserRowMapper());
    }

    @Override
    public void save(User item) {

    }

    @Override
    public void removeById(Long id) {

    }


    @Override
    protected String getTableName() {
        return null;
    }


}
