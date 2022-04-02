package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Order;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.mapper.OrderRowMapper;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao{

    private static final String GET_ORDERS_IN_PROCESS_QUERY = "select * from orders where user_id = %d and is_taken = false and is_ordered = false; ";
    private static final String INSERT_ORDER_QUERY = "insert into orders set user_id = %d; ";
    private static final String SET_ORDER = "update orders set visiting_time = ?, payment_type = ?, is_ordered = true where id = ?;";

    public OrderDaoImpl(Connection connection) {
        super(connection, new OrderRowMapper(), Order.TABLE_NAME);
    }

    @Override
    protected Map<String, Object> getFields(Order item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(Order.ID, item.getId());
        fields.put(Order.VISITING_TIME, item.getVisitingTime());
        fields.put(Order.USER_ID, item.getUserId());
        fields.put(Order.PAYMENT_TYPE, item.getPaymentType());
        fields.put(Order.RATING, item.getRating());
        fields.put(Order.COMMENT, item.getComment());
        fields.put(Order.IS_TAKEN, item.isTaken());
        fields.put(Order.IS_ORDERED, item.isOrdered());
        return fields;
    }

    @Override
    public Long getOrderIdInProcess(Long userId) throws DaoException {
        String query = String.format(GET_ORDERS_IN_PROCESS_QUERY, userId);
        List<Order> orders = executeQuery(query);
        if (orders.isEmpty()) {
            return null;
        } else {
            return orders.get(0).getId();
        }
    }

    @Override
    public Long createOrder(Long userId) throws DaoException {
        String query = String.format(INSERT_ORDER_QUERY, userId);
        executeUpdate(query);
        return getOrderIdInProcess(userId);
    }

    @Override
    public void setOrder(Long orderId, LocalDateTime date, String paymentType) throws DaoException {
        executeUpdate(SET_ORDER, date, paymentType, orderId);
    }
}
