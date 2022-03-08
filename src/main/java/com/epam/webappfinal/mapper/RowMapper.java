package com.epam.webappfinal.mapper;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.Identifiable;
import com.epam.webappfinal.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifiable> {

    T map (ResultSet resultSet) throws SQLException;

    static RowMapper<? extends Identifiable> create(String table) {
        switch (table) {
            case User.TABLE_NAME:
                return  new UserRowMapper();
            case Food.TABLE_NAME:
                return new FoodRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table = " + table);
        }
    }

}
