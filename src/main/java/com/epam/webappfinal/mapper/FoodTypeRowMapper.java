package com.epam.webappfinal.mapper;

import com.epam.webappfinal.entity.FoodType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodTypeRowMapper implements RowMapper<FoodType> {
    @Override
    public FoodType map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(FoodType.ID);
        String name = resultSet.getString(FoodType.NAME);

        return new FoodType(id, name);
    }
}
