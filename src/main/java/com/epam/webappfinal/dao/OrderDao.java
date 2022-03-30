package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Order;
import com.epam.webappfinal.exception.DaoException;

import java.time.LocalDateTime;

public interface OrderDao extends Dao<Order> {

    Long getOrderIdInProcess(Long userId) throws DaoException;

    Long makeOrder(Long userId) throws DaoException;

    void setOrder(Long orderId, LocalDateTime date, String paymentType) throws DaoException;
}
