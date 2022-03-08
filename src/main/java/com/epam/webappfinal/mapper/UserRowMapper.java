package com.epam.webappfinal.mapper;

import com.epam.webappfinal.entity.Identifiable;
import com.epam.webappfinal.entity.User;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper{
    @Override
    public Identifiable map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(User.ID);
        String name = resultSet.getString(User.NAME);
        String surname = resultSet.getString(User.SURNAME);
        String login = resultSet.getString(User.LOGIN);
        boolean isAdmin = resultSet.getBoolean(User.IS_ADMIN);
        BigDecimal amount = resultSet.getBigDecimal(User.AMOUNT);
        int loyaltyPoints = resultSet.getInt(User.LOYALTY_POINTS);
        boolean isBlocked = resultSet.getBoolean(User.IS_BLOCKED);
        return new User(id, name, surname, login, isAdmin, amount,loyaltyPoints, isBlocked);
    }
}
