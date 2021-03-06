package com.epam.webappfinal.service;

import com.epam.webappfinal.dao.*;
import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.Order;
import com.epam.webappfinal.entity.PaymentType;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.exception.ServiceException;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderServiceImpl implements OrderService{

    private final Logger LOGGER = LogManager.getLogger();

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
                orderId = orderDao.createOrder(userId);
            }

            OrdersFoodDao ordersFoodDao = helper.createOrdersFoodDao();
            Long orderFoodId = ordersFoodDao.getOrderFoodId(orderId, foodId);
            if (orderFoodId == null) {
                ordersFoodDao.makeOrderFood(orderId, foodId);
            } else {
                ordersFoodDao.operateWithOrderDao(orderId, foodId, OperationType.INCREMENT);
            }
            LOGGER.debug("Food item is added to shopping cart");
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
            LOGGER.debug("Amount of food is decremented at the shopping cart");
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
            LOGGER.debug("List of food has got in shopping cart");
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

    @Override
    public boolean payOrder(User user, BigDecimal totalAmount, String dateStr, PaymentType paymentType) throws ServiceException {
        if ("".equals(dateStr)) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime date = LocalDateTime.parse(dateStr);
        if ((date.compareTo(now) <= 0) || (user.getAmount().compareTo(totalAmount) == -1)) {
            return false;
        }
        if (user.getAmount().subtract(totalAmount).compareTo(new BigDecimal(0)) < 0) {
            return false;
        }

        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            if (paymentType.equals(PaymentType.ACCOUNT)) {
                user.setAmount(user.getAmount().subtract(totalAmount));
            }
            UserDao userDao = helper.createUserDao();
            userDao.save(user);

            OrderDao orderDao = helper.createOrderDao();
            Long orderId = orderDao.getOrderIdInProcess(user.getId());
            orderDao.setOrder(orderId, date, paymentType.toString().toLowerCase(Locale.ROOT));
            LOGGER.debug("Order has done");
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return true;
    }

    @Override
    public List<ImmutableTriple<Order, List<Food>, BigDecimal>> getOrdersWithFood(Long userId) throws ServiceException {
        List<ImmutableTriple<Order, List<Food>, BigDecimal>> ordersWithFood;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            OrdersFoodDao ordersFoodDao = helper.createOrdersFoodDao();
            ordersWithFood = ordersFoodDao.getOrdersWithFood(userId);
            LOGGER.debug("Food list with total price and orders has got by user id");
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return  ordersWithFood;
    }

    @Override
    public List<ImmutableTriple<Order, List<Food>, BigDecimal>> getOrdersWithFood() throws ServiceException {
        List<ImmutableTriple<Order, List<Food>, BigDecimal>> ordersWithFood;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            OrdersFoodDao ordersFoodDao = helper.createOrdersFoodDao();
            ordersWithFood = ordersFoodDao.getOrdersWithFood();
            LOGGER.debug("Food list with total price and orders has got");
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return  ordersWithFood;
    }

    @Override
    public void placeAnOrder(Long orderId, OperationType operationType) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            OrderDao orderDao = helper.createOrderDao();
            orderDao.updateStatus(orderId, operationType);
            LOGGER.debug("Order is handled");
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
