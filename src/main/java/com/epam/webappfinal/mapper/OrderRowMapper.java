package com.epam.webappfinal.mapper;

import com.epam.webappfinal.entity.Order;
import com.epam.webappfinal.entity.OrderStatus;
import com.epam.webappfinal.entity.PaymentType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Locale;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Order.ID);

        String visitingTimeStr = resultSet.getString(Order.VISITING_TIME);
        visitingTimeStr = visitingTimeStr.replace(" ", "T");
        LocalDateTime visitingTime = LocalDateTime.parse(visitingTimeStr);

        Long userId = resultSet.getLong(Order.USER_ID);
        String paymentTypeLowerCaseLine = resultSet.getString(Order.PAYMENT_TYPE);
        String paymentTypeUpperCaseLine = paymentTypeLowerCaseLine.toUpperCase(Locale.ROOT);
        PaymentType type = PaymentType.valueOf(paymentTypeUpperCaseLine);

        String orderStatusLowerCaseLine = resultSet.getString(Order.ORDER_STATUS);
        String orderStatusUpperCaseLine = orderStatusLowerCaseLine.toUpperCase(Locale.ROOT);
        OrderStatus orderStatus = OrderStatus.valueOf(orderStatusUpperCaseLine);

        return new Order(id, visitingTime, userId, type, orderStatus);
    }
}
