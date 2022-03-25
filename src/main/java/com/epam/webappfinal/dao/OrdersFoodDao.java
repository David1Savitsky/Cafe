package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.OrdersFood;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.service.OperationType;
import javafx.util.Pair;

import java.util.List;

public interface OrdersFoodDao extends Dao<OrdersFood> {

    Long getOrderFoodId(Long orderId, Long foodId) throws DaoException;

    Long makeOrderFood(Long orderId, Long foodId) throws DaoException;

//    void deleteOrderFood(Long orderId, Long foodId) throws DaoException;
//
//    void incrementOrderFood(Long orderId, Long foodId) throws DaoException;

    void operateWithOrderDao(Long orderId, Long foodId, OperationType operation) throws DaoException;

    List<Pair<Food, Integer>> getFoodInShoppingCart(Long orderId) throws DaoException;

}
