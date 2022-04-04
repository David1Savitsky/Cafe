package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.Order;
import com.epam.webappfinal.entity.OrdersFood;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.mapper.FoodRowMapper;
import com.epam.webappfinal.mapper.OrderRowMapper;
import com.epam.webappfinal.mapper.OrdersFoodRowMapper;
import com.epam.webappfinal.service.OperationType;
import javafx.util.Pair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

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
    private static final String GET_FOOD_IN_SHOPPING_CART_QUERY = "select food.id, food.name, food.price, orders_food.count from food join orders_food on food.id = orders_food.food_id and orders_food.order_id = %d and food.is_disabled = false; ";
    private static final String DELETE_ORDER_FOOD_QUERY = "delete from orders_food where order_id = %d and food_id = %d; ";
    private static final String GET_ORDERS_WITH_FOOD_QUERY =
                    "SELECT o.*, f.*\n" +
                    "from orders_food o_f INNER JOIN food f on o_f.food_id=f.id\n" +
                    "  INNER JOIN orders o on o_f.order_id=o.id\n" +
                    "  INNER JOIN users u ON o.user_id=u.id\n" +
                    " WHERE u.id = %d and status = 'is_ordered'; ";

    private static final String GET_ALL_ORDERS_WITH_FOOD_QUERY =
            "SELECT o.*, f.*\n" +
                    "from orders_food o_f INNER JOIN food f on o_f.food_id=f.id\n" +
                    "  INNER JOIN orders o on o_f.order_id=o.id\n" +
                    "  INNER JOIN users u ON o.user_id=u.id\n" +
                    " WHERE status = 'is_ordered' or status = 'is_taken' or status = 'is_rejected'; ";

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

    @Override
    public List<ImmutableTriple<Order, List<Food>, BigDecimal>> getOrdersWithFood(Long userId) throws DaoException {
        String query = String.format(GET_ORDERS_WITH_FOOD_QUERY, userId);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<ImmutableTriple<Order, List<Food>, BigDecimal>> list = extractFoodNamesFromResultSet(resultSet);
            return list;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ImmutableTriple<Order, List<Food>, BigDecimal>> getOrdersWithFood() throws DaoException {
        String query = String.format(GET_ALL_ORDERS_WITH_FOOD_QUERY);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<ImmutableTriple<Order, List<Food>, BigDecimal>> list = extractFoodNamesFromResultSet(resultSet);
            return list;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private List<ImmutableTriple<Order, List<Food>, BigDecimal>> extractFoodNamesFromResultSet(ResultSet resultSet) throws SQLException {
        OrderRowMapper orderRowMapper = new OrderRowMapper();
        FoodRowMapper foodRowMapper = new FoodRowMapper();
        List<ImmutableTriple<Order, List<Food>, BigDecimal>> ordersWithFood = new ArrayList<>();
        List<Food> foodList = new ArrayList<>();
        int prevOrderId = 0;
        Order order = null;
        while (resultSet.next()) {
            int currOrderId = resultSet.getInt("o.id");
            if (prevOrderId == currOrderId) {
                Food food = foodRowMapper.map(resultSet);
                food.setId(resultSet.getLong("f.id"));
                foodList.add(food);
            } else {
                if (order != null) {
                    ordersWithFood.add(new ImmutableTriple<>(order, new ArrayList<>(foodList), sumFoodPrice(foodList)));
                    foodList.clear();
                }
                order = orderRowMapper.map(resultSet);
                Food food = foodRowMapper.map(resultSet);
                food.setId(resultSet.getLong("f.id"));
                foodList.add(food);
                prevOrderId = currOrderId;
            }
        }
        if (!foodList.isEmpty()) {
            ordersWithFood.add(new ImmutableTriple<>(order, new ArrayList<>(foodList), sumFoodPrice(foodList)));
            foodList.clear();
        }
        return ordersWithFood;
    }

    private BigDecimal sumFoodPrice(List<Food> foodList) {
        BigDecimal sum = new BigDecimal(0);
        for (Food food : foodList) {
            sum = sum.add(food.getPrice());
        }
        return sum;
    }
}
