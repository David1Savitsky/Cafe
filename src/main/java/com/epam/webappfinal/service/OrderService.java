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

public interface OrderService {

    void addFoodToShoppingCart(Long foodId, Long userId) throws ServiceException;

    void decrementFoodFromShoppingCart(Long foodId, Long userId, Integer quantity) throws ServiceException;

    void operateWithFoodFromShoppingCart(Long foodId, Long userId, OperationType operation) throws ServiceException;

    List<Pair<Food, Integer>> getFoodInShoppingCart(Long userId) throws ServiceException;

    BigDecimal getTotalAmount(List<Pair<Food, Integer>> foodList, Integer loyaltyPoints);

    boolean payOrder(User user, BigDecimal totalAmount, String dateStr, PaymentType paymentType) throws ServiceException;

    List<ImmutableTriple<Order, List<Food>, BigDecimal>> getOrdersWithFood(Long userId) throws ServiceException;

    void changeRating(Long orderId, int rating) throws ServiceException;

    void placeAnOrder(Long orderId) throws ServiceException;
}
