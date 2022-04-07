package com.epam.webappfinal.mapper;

import com.epam.webappfinal.entity.OrdersFood;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersFoodRowMapper implements RowMapper<OrdersFood> {
    @Override
    public OrdersFood map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(OrdersFood.ID);
        Long orderId = resultSet.getLong(OrdersFood.ORDER_ID);
        Long foodId = resultSet.getLong(OrdersFood.FOOD_ID);
        Integer count = resultSet.getInt(OrdersFood.COUNT);
        return new OrdersFood(id, orderId, foodId, count);
    }
}
