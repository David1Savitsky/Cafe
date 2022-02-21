package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;

import java.sql.*;
import java.util.Optional;

public class SimpleUserDao implements  UserDao{
    @Override
    public Optional<User> findUserByLoginAndPassword(final String login, final String password) throws DaoException {
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("", "root", "root");
            try (PreparedStatement statement =connection.prepareStatement("")) {  //Hash of password use MD5 or SHA128/256
                statement.setString(1, login);
                statement.setString(2, password);
                try (ResultSet resultSet =statement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(new User(resultSet.getLong("ID"), resultSet.getString("LOGIN")));
                    }
                }
            }
            return Optional.empty();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException(e);
        }
    }
}
