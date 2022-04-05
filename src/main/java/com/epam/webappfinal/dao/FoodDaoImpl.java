package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.mapper.FoodRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FoodDaoImpl extends AbstractDao<Food> implements FoodDao{

    private static final String GET_ALL_NOT_DISABLED_QUERY = "SELECT * FROM %s WHERE is_disabled = false ; ";
    private static final String GET_TYPE_NOT_DISABLED_QUERY = "select * from %s where type_id = %d and is_disabled = false; ";
    private static final String UPDATE_FOOD_NOT_DISABLED_QUERY = "update food set is_disabled = true where id = ?; ";
    private static final String UPDATE_RATING_QUERY = "update ratings set rating = ? where food_id = ? and user_id = ?; ";

    public FoodDaoImpl(Connection connection) {
        super(connection, new FoodRowMapper(), Food.TABLE_NAME);
    }

    @Override
    protected Map<String, Object> getFields(Food item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(Food.ID, item.getId());
        fields.put(Food.NAME, item.getName());
        fields.put(Food.TYPE_ID, item.getTypeId());
        fields.put(Food.PRICE, item.getPrice());
        fields.put(Food.IS_DISABLED, item.getDisabled());
        return fields;
    }

    @Override
    public List<Food> getAllNotDisabled() throws DaoException {
        String query = String.format(GET_ALL_NOT_DISABLED_QUERY, Food.TABLE_NAME);
        return executeQuery(query);
    }

    @Override
    public List<Food> getAllNotDisabled(Long typeId) throws DaoException {
        String query = String.format(GET_TYPE_NOT_DISABLED_QUERY, Food.TABLE_NAME, typeId);
        return executeQuery(query);
    }

    @Override
    public void makeDisabledFoodById(Long foodId) throws DaoException {
        executeUpdate(UPDATE_FOOD_NOT_DISABLED_QUERY, foodId);
    }

    @Override
    public void updateRating(Long foodId, Long userId, int rating) throws DaoException {
        executeUpdate(UPDATE_RATING_QUERY, rating, foodId, userId);
    }
}
