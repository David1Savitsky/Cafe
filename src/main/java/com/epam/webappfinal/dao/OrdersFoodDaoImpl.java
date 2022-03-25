package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.OrdersFood;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.mapper.OrdersFoodRowMapper;
import com.epam.webappfinal.service.OperationType;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrdersFoodDaoImpl extends AbstractDao<OrdersFood> implements OrdersFoodDao{

    private static final String GET_ORDERS_FOOD_QUERY = "select * from orders_food where order_id = %d and food_id = %d; ";
    private static final String INSERT_ORDER_FOOD_QUERY = "insert into orders_food set order_id = %d, food_id = %d, count = 1; ";
    private static final String INCREMENT_COUNT_QUERY = "update orders_food set count = count + 1 where order_id = %d and food_id = %d; ";
    private static final String DECREMENT_COUNT_QUERY = "update orders_food set count = count - 1 where order_id = %d and food_id = %d; ";
    private static final String GET_FOOD_IN_SHOPPING_CART_QUERY = "select food.id, food.name, food.price, orders_food.count from food join orders_food on food.id = orders_food.food_id and orders_food.order_id = %d; ";
    private static final String DELETE_ORDER_FOOD_QUERY = "delete from orders_food where order_id = %d and food_id = %d; ";

    private final Connection connection;

    public OrdersFoodDaoImpl(Connection connection) {
        super(connection, new OrdersFoodRowMapper(), OrdersFood.TABLE_NAME );
        this.connection = connection;
    }

    @Override
    protected Map<String, Object> getFields(OrdersFood item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(OrdersFood.ID, item.getId());
        fields.put(OrdersFood.ORDER_ID, item.getOrderId());
        fields.put(OrdersFood.FOOD_ID, item.getFoodId());
        fields.put(OrdersFood.COUNT, item.getCount());
        return fields;
    }

    @Override
    public Long getOrderFoodId(Long orderId, Long foodId) throws DaoException {
        String query = String.format(GET_ORDERS_FOOD_QUERY, orderId, foodId);
        List<OrdersFood> ordersFoodList = executeQuery(query);
        if (ordersFoodList.isEmpty()) {
            return null;
        } else {
            return ordersFoodList.get(0).getId();
        }
    }

    @Override
    public Long makeOrderFood(Long orderId, Long foodId) throws DaoException {
        String query = String.format(INSERT_ORDER_FOOD_QUERY, orderId, foodId);
        executeUpdate(query);
        return getOrderFoodId(orderId, foodId);
    }

    @Override
    public void operateWithOrderDao(Long orderId, Long foodId, OperationType operation) throws DaoException {
        String query;
        switch (operation) {
            case DELETE:
                query = String.format(DELETE_ORDER_FOOD_QUERY, orderId, foodId);
                break;
            case INCREMENT:
                query = String.format(INCREMENT_COUNT_QUERY, orderId, foodId);
                break;
            case DECREMENT:
                query = String.format(DECREMENT_COUNT_QUERY, orderId, foodId);
                break;
            default:
                throw new IllegalArgumentException("Unknown operation type = " + operation);
        }
        executeUpdate(query);
    }

//    @Override
//    public void deleteOrderFood(Long orderId, Long foodId) throws DaoException {
//        String query = String.format(DELETE_ORDER_FOOD_QUERY, orderId, foodId);
//        executeUpdate(query);
//    }
//
//    @Override
//    public void incrementOrderFood(Long orderId, Long foodId) throws DaoException {
//        String query = String.format(INCREMENT_COUNT_QUERY, orderId, foodId);
//        executeUpdate(query);
//    }

    @Override
    public List<Pair<Food, Integer>> getFoodInShoppingCart(Long orderId) throws DaoException {
        String query = String.format(GET_FOOD_IN_SHOPPING_CART_QUERY, orderId);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            return extractFoodFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private List<Pair<Food, Integer>> extractFoodFromResultSet(ResultSet resultSet) throws SQLException {
        List<Pair<Food, Integer>> foodInShopCartList = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong(Food.ID);
            String name = resultSet.getString(Food.NAME);
            BigDecimal price = resultSet.getBigDecimal(Food.PRICE);
            Integer count = resultSet.getInt(OrdersFood.COUNT);
            Food food = new Food(id, name, null, price, null);
            foodInShopCartList.add(new Pair<>(food, count));
        }
        return foodInShopCartList;
    }
}
