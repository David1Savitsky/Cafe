package com.epam.webappfinal.mapper;

import com.epam.webappfinal.entity.Food;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodRowMapper implements RowMapper<Food>{
    @Override
    public Food map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Food.ID);
        String name = resultSet.getString(Food.NAME);
        Long typeId = resultSet.getLong(Food.TYPE_ID);
        BigDecimal price = resultSet.getBigDecimal(Food.PRICE);
        Boolean isDisabled = resultSet.getBoolean(Food.IS_DISABLED);

        return new Food(id, name, typeId, price, isDisabled);
    }


}
