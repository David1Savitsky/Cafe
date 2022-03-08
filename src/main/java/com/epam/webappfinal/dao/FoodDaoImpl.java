package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.mapper.FoodRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class FoodDaoImpl extends AbstractDao<Food> implements FoodDao{

    private static final String GET_ALL_NOT_DISABLED_QUERY = "SELECT * FROM %s WHERE is_disabled = false ; ";
    private static final String GET_DRINK_TYPE_NOT_DISABLED_QUERY = "select * from %s where type = \"drink\" and is_disabled = false; ";
    private static final String GET_MEAL_TYPE_NOT_DISABLED_QUERY = "select * from %s where type = \"meal\" and is_disabled = false; ";

    public FoodDaoImpl(Connection connection) {
        super(connection, new FoodRowMapper(), Food.TABLE_NAME);
    }

    @Override
    protected Map<String, Object> getFields(Food item) {
        return null;
    }

    @Override
    public List<Food> getAllNotDisabled() throws DaoException {
        String query = String.format(GET_ALL_NOT_DISABLED_QUERY, Food.TABLE_NAME);
        return executeQuery(query);
    }

    @Override
    public List<Food> getAllNotDisabledDrinks() throws DaoException {
        String query = String.format(GET_DRINK_TYPE_NOT_DISABLED_QUERY, Food.TABLE_NAME);
        return executeQuery(query);
    }

    @Override
    public List<Food> getAllNotDisabledMeal() throws DaoException {
        String query = String.format(GET_MEAL_TYPE_NOT_DISABLED_QUERY, Food.TABLE_NAME);
        return executeQuery(query);
    }
}
