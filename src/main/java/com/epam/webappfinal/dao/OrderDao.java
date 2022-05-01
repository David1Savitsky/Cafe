package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Order;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.service.OperationType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This interface declares the methods that will interact with database.
 *
 * @author David Savitsky
 * @version 1.0
 * @since 1.0
 */
public interface OrderDao extends Dao<Order> {

    /**
     * This method gets the user's order in process.
     *
     * @param userId user's id.
     * @return {@code Long} order's id.
     * @throws DaoException if any dao exception occurred during processing.
     */
    Long getOrderIdInProcess(Long userId) throws DaoException;

    /**
     * This method creates the user's order.
     *
     * @param userId user's id.
     * @return {@code Long} order's id.
     * @throws DaoException if any dao exception occurred during processing.
     */
    Long createOrder(Long userId) throws DaoException;

    /**
     * This method creates user's order.
     *
     * @param orderId order's id.
     * @param date date of receipt of the order.
     * @param paymentType payment type.
     * @throws DaoException if any dao exception occurred during processing.
     */
    void setOrder(Long orderId, LocalDateTime date, String paymentType) throws DaoException;

    /**
     * This method gets list of user's orders.
     *
     * @param userId user's id.
     * @return {@code List<Order>} list of orders.
     * @throws DaoException if any dao exception occurred during processing.
     */
    List<Order> getOrdersByUserId(Long userId) throws DaoException;

    /**
     * This method updates the order's status.
     *
     * @param orderId       order's id.
     * @param operationType operation type.
     * @throws DaoException if any dao exception occurred during processing.
     */
    void updateStatus(Long orderId, OperationType operationType) throws DaoException;
}
