package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Order;
import com.epam.webappfinal.exception.DaoException;

public interface OrderDao extends Dao<Order> {

    Long getOrderIdInProcess(Long userId) throws DaoException;

    Long makeOrder(Long userId) throws DaoException;
}
