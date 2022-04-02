package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.FoodType;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.mapper.FoodTypeRowMapper;
import com.epam.webappfinal.mapper.RowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FoodTypeDaoImpl extends AbstractDao<FoodType> implements FoodTypeDao {

    private static final String GET_ALL_TYPES_QUERY = "select * from types; ";

    public FoodTypeDaoImpl(Connection connection) {
        super(connection, new FoodTypeRowMapper(), FoodType.TABLE_NAME);
    }

    @Override
    protected Map<String, Object> getFields(FoodType item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(FoodType.ID, item.getId());
        fields.put(FoodType.NAME, item.getName());
        return fields;
    }

    @Override
    public List<FoodType> getTypes() throws DaoException {
        return executeQuery(GET_ALL_TYPES_QUERY);
    }
}
