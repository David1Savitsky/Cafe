package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Order;
import com.epam.webappfinal.exception.DaoException;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDao extends Dao<Order> {

    Long getOrderIdInProcess(Long userId) throws DaoException;

    Long createOrder(Long userId) throws DaoException;

    void setOrder(Long orderId, LocalDateTime date, String paymentType) throws DaoException;

    List<Order> getOrdersByUserId(Long userId) throws DaoException;

    void updateRating(Long orderId, int rating) throws DaoException;

    void updateIsTaken(Long orderId) throws DaoException;
}
