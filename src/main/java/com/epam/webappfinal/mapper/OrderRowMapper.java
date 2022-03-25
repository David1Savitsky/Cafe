package com.epam.webappfinal.mapper;

import com.epam.webappfinal.entity.Identifiable;
import com.epam.webappfinal.entity.Order;
import com.epam.webappfinal.entity.PaymentType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Locale;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Order.ID);
        Date visitingTime = resultSet.getDate(Order.VISITING_TIME);
        Long userId = resultSet.getLong(Order.USER_ID);

        String paymentTypeLowerCaseLine = resultSet.getString(Order.PAYMENT_TYPE);
        String paymentTypeUpperCaseLine = paymentTypeLowerCaseLine.toUpperCase(Locale.ROOT);
        PaymentType type = PaymentType.valueOf(paymentTypeUpperCaseLine);

        Integer rating = resultSet.getInt(Order.RATING);
        String comment = resultSet.getString(Order.COMMENT);
        boolean isTaken = resultSet.getBoolean(Order.IS_TAKEN);
        boolean isOrdered = resultSet.getBoolean(Order.IS_ORDERED);

        return new Order(id, visitingTime, userId, type, rating, comment, isTaken, isOrdered);
    }
}
