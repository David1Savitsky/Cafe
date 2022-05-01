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

/**
 * This interface declares the methods that will interact with database.
 *
 * @author David Savitsky
 * @version 1.0
 * @since 1.0
 */
public interface OrdersFoodDao extends Dao<OrdersFood> {

    /**
     * This method gets the orderFood's id.
     *
     * @param orderId order's id.
     * @param foodId  food's id.
     * @return {@code Long} orderFood's id.
     * @throws DaoException if any dao exception occurred during processing.
     */
    Long getOrderFoodId(Long orderId, Long foodId) throws DaoException;

    /**
     * This method makes the orderFood's item.
     *
     * @param orderId order's id.
     * @param foodId  food's id.
     * @return {@code Long} orderFood's id.
     * @throws DaoException if any dao exception occurred during processing.
     */
    Long makeOrderFood(Long orderId, Long foodId) throws DaoException;

    /**
     * This method operates with order database.
     *
     * @param orderId order's id.
     * @param foodId  food's id.
     * @param operation  operation type.
     * @throws DaoException if any dao exception occurred during processing.
     */
    void operateWithOrderDao(Long orderId, Long foodId, OperationType operation) throws DaoException;

    /**
     * This method gets the list of food with its prices.
     *
     * @param orderId order's id.
     * @return {@code Long} list of food with its prices.
     * @throws DaoException if any dao exception occurred during processing.
     */
    List<Pair<Food, Integer>> getFoodInShoppingCart(Long orderId) throws DaoException;

    /**
     * This method gets the food's list with its prices of user's orders.
     *
     * @param userId user's id.
     * @throws DaoException if any dao exception occurred during processing.
     */
    List<ImmutableTriple<Order, List<Food>, BigDecimal>> getOrdersWithFood(Long userId) throws DaoException;

    /**
     * This method gets the food's list with its prices of all orders.
     *
     * @throws DaoException if any dao exception occurred during processing.
     * @return {@code  List<ImmutableTriple<Order, List<Food>, BigDecimal>>} food's list with its prices of all orders.
     */
    List<ImmutableTriple<Order, List<Food>, BigDecimal>> getOrdersWithFood() throws DaoException;
}
