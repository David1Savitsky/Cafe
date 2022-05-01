package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Order;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.mapper.OrderRowMapper;
import com.epam.webappfinal.service.OperationType;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao{

    private static final String GET_ORDERS_IN_PROCESS_QUERY = "select * from orders where user_id = %d and status = 'is_choosing'; ";
    private static final String GET_ORDERS_BY_USER_ID_QUERY = "select * from orders where user_id = ? and status = 'is_ordered' order by visiting_time;";
    private static final String INSERT_ORDER_QUERY = "insert into orders set user_id = %d; ";
    private static final String SET_ORDER = "update orders set visiting_time = ?, payment_type = ?, status = 'is_ordered' where id = ?;";
    private static final String IS_TAKEN_QUERY = "update orders set status = 'is_taken' where id = ?; ";
    private static final String IS_REJECTED_QUERY = "update orders set status = 'is_rejected' where id = ?; ";

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
        fields.put(Order.ORDER_STATUS, item.getOrderStatus());

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

    @Override
    public List<Order> getOrdersByUserId(Long userId) throws DaoException {
        return executeQuery(GET_ORDERS_BY_USER_ID_QUERY, userId);
    }

    @Override
    public void updateStatus(Long orderId, OperationType operationType) throws DaoException {
        switch (operationType) {
            case ACCEPT:
                executeUpdate(IS_TAKEN_QUERY, orderId);
                break;
            case DELETE:
                executeUpdate(IS_REJECTED_QUERY, orderId);
                break;
            default:
                throw new IllegalArgumentException("Unknown operation type = " + operationType);
        }

    }
}
