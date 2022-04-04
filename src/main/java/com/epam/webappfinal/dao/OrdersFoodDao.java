package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.Order;
import com.epam.webappfinal.entity.OrdersFood;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.service.OperationType;
import javafx.util.Pair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.math.BigDecimal;
import java.util.List;

public interface OrdersFoodDao extends Dao<OrdersFood> {

    Long getOrderFoodId(Long orderId, Long foodId) throws DaoException;

    Long makeOrderFood(Long orderId, Long foodId) throws DaoException;

    void operateWithOrderDao(Long orderId, Long foodId, OperationType operation) throws DaoException;

    List<Pair<Food, Integer>> getFoodInShoppingCart(Long orderId) throws DaoException;

    List<ImmutableTriple<Order, List<Food>, BigDecimal>> getOrdersWithFood(Long userId) throws DaoException;
    //List<Pair<Order, List<Food>>> getFoodByOrdersId(List<Order> orders) throws DaoException;
}
