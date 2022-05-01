package com.epam.webappfinal.service;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.Order;
import com.epam.webappfinal.entity.PaymentType;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import javafx.util.Pair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.math.BigDecimal;
import java.util.List;

/**
 * This interface declares the methods that will interact with Command and Dao
 * layer. Methods interact with {@code Order}.
 *
 * @author David Savitsky
 * @version 1.0
 * @since 1.0
 */
public interface OrderService {

    /**
     * This method add food to the user's shopping cart.
     *
     * @param foodId    food's id.
     * @param userId    user's id.
     * @throws ServiceException if any service exception occurred during processing.
     */
    void addFoodToShoppingCart(Long foodId, Long userId) throws ServiceException;

    /**
     * This method decrement food item in the user's shopping cart.
     *
     * @param foodId    food's id.
     * @param userId    user's id.
     * @param quantity  amount of food in the shopping cart.
     * @throws ServiceException if any service exception occurred during processing.
     */
    void decrementFoodFromShoppingCart(Long foodId, Long userId, Integer quantity) throws ServiceException;

    /**
     * This method operate with food int the user's shopping cart.
     *
     * @param foodId    food's id.
     * @param userId    user's id.
     * @param operation type of operation which is held with food.
     * @throws ServiceException if any service exception occurred during processing.
     */
    void operateWithFoodFromShoppingCart(Long foodId, Long userId, OperationType operation) throws ServiceException;

    /**
     * This method gets all the food in the user's shopping cart.
     *
     * @param userId    user's id.
     * @return {@code List<Pair<Food, Integer>>} list of food with its price.
     * @throws ServiceException if any service exception occurred during processing.
     */
    List<Pair<Food, Integer>> getFoodInShoppingCart(Long userId) throws ServiceException;

    /**
     * This method gets total price of food in the user's shopping cart.
     *
     * @param foodList         list of food.
     * @param loyaltyPoints    user's amount of loyalty points.
     * @return {@code BigDecimal} total price.
     */
    BigDecimal getTotalAmount(List<Pair<Food, Integer>> foodList, Integer loyaltyPoints);

    /**
     * This method provides @{code user} pay the order.
     *
     * @param user           user.
     * @param totalAmount    total price of the order.
     * @param paymentType    user's payment type.
     * @return {@code true} if the operation was held successfully.
     * @throws ServiceException if any service exception occurred during processing.
     */
    boolean payOrder(User user, BigDecimal totalAmount, String dateStr, PaymentType paymentType) throws ServiceException;

    /**
     * This purpose of the method is to get list of food with its prices of orders by user id.
     *
     * @param userId           user's id.
     * @return {@code List<ImmutableTriple<Order, List<Food>, BigDecimal>>} list of food with its prices of all orders.
     * @throws ServiceException if any service exception occurred during processing.
     */
    List<ImmutableTriple<Order, List<Food>, BigDecimal>> getOrdersWithFood(Long userId) throws ServiceException;

    /**
     * This purpose of the method is to get list of food with its prices of all orders.
     *
     * @return {@code List<ImmutableTriple<Order, List<Food>, BigDecimal>>} list of food with its prices of all orders.
     * @throws ServiceException if any service exception occurred during processing.
     */
    List<ImmutableTriple<Order, List<Food>, BigDecimal>> getOrdersWithFood() throws ServiceException;

    /**
     * This purpose of the method is to access or deny client's order by order id.
     *
     * @param orderId order's id.
     * @param operationType operation type.
     * @throws ServiceException if any service exception occurred during processing.
     */
    void placeAnOrder(Long orderId, OperationType operationType) throws ServiceException;
}
