package com.epam.webappfinal.service;

import com.epam.webappfinal.dao.DaoHelper;
import com.epam.webappfinal.dao.DaoHelperFactory;
import com.epam.webappfinal.dao.OrderDao;
import com.epam.webappfinal.dao.OrdersFoodDao;
import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.exception.ServiceException;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class OrderServiceImpl implements OrderService{

    private static final Double DISCOUNT_FACTOR = 0.001;

    private final DaoHelperFactory daoHelperFactory;

    public OrderServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public void addFoodToShoppingCart(Long foodId, Long userId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            OrderDao orderDao = helper.createOrderDao();
            Long orderId = orderDao.getOrderIdInProcess(userId);
            if (orderId == null) {
                orderId = orderDao.makeOrder(userId);
            }

            OrdersFoodDao ordersFoodDao = helper.createOrdersFoodDao();
            Long orderFoodId = ordersFoodDao.getOrderFoodId(orderId, foodId);
            if (orderFoodId == null) {
                ordersFoodDao.makeOrderFood(orderId, foodId);
            } else {
                ordersFoodDao.operateWithOrderDao(orderId, foodId, OperationType.INCREMENT);
            }
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void decrementFoodFromShoppingCart(Long foodId, Long userId, Integer quantity) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            OrderDao orderDao = helper.createOrderDao();
            Long orderId = orderDao.getOrderIdInProcess(userId);
            OrdersFoodDao ordersFoodDao = helper.createOrdersFoodDao();
            if (quantity > 1) {
                ordersFoodDao.operateWithOrderDao(orderId, foodId, OperationType.DECREMENT);
            } else {
                ordersFoodDao.operateWithOrderDao(orderId, foodId, OperationType.DELETE);
            }
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void operateWithFoodFromShoppingCart(Long foodId, Long userId, OperationType operation) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            OrderDao orderDao = helper.createOrderDao();
            Long orderId = orderDao.getOrderIdInProcess(userId);
            OrdersFoodDao ordersFoodDao = helper.createOrdersFoodDao();
            switch (operation) {
                case DELETE:
                    ordersFoodDao.operateWithOrderDao(orderId, foodId, OperationType.DELETE);
                    break;
                case INCREMENT:
                    ordersFoodDao.operateWithOrderDao(orderId, foodId, OperationType.INCREMENT);
                    break;
                case DECREMENT:

                    ordersFoodDao.operateWithOrderDao(orderId, foodId, OperationType. DECREMENT);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown operation type = " + operation);
            }

            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Pair<Food, Integer>> getFoodInShoppingCart(Long userId) throws ServiceException {
        List<Pair<Food, Integer>> foodList;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            OrderDao orderDao = helper.createOrderDao();
            Long orderId = orderDao.getOrderIdInProcess(userId);
            OrdersFoodDao ordersFoodDao = helper.createOrdersFoodDao();
            foodList = ordersFoodDao.getFoodInShoppingCart(orderId);

            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return foodList;
    }

    @Override
    public BigDecimal getTotalAmount(List<Pair<Food, Integer>> foodList, Integer loyaltyPoints) {
        BigDecimal totalAmount = new BigDecimal("0");
        BigDecimal itemAmount;
        BigDecimal itemPrice;
        for (Pair<Food, Integer> food: foodList) {
            itemPrice = food.getKey().getPrice();
            itemAmount = itemPrice.multiply(new BigDecimal(food.getValue()));
            totalAmount = totalAmount.add(itemAmount);
        }

        BigDecimal discount = totalAmount.multiply(new BigDecimal(loyaltyPoints * DISCOUNT_FACTOR));
        discount = discount.setScale(0, RoundingMode.HALF_UP);
        return totalAmount.subtract(discount);
    }
}
