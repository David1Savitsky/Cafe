package com.epam.webappfinal.mapper;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.FoodType;
import com.epam.webappfinal.entity.Identifiable;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class FoodRowMapper implements RowMapper<Food>{
    @Override
    public Food map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Food.ID);
        String name = resultSet.getString(Food.NAME);

        String foodTypeLowerCaseLine = resultSet.getString(Food.TYPE);
        String foodTypeUpperCaseLine = foodTypeLowerCaseLine.toUpperCase(Locale.ROOT);
        FoodType type = FoodType.valueOf(foodTypeUpperCaseLine);

        BigDecimal price = resultSet.getBigDecimal(Food.PRICE);
        Boolean isDisabled = resultSet.getBoolean(Food.IS_DISABLED);

        return new Food(id, name, type, price, isDisabled);
    }
}
