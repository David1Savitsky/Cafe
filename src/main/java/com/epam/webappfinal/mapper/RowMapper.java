package com.epam.webappfinal.mapper;

import com.epam.webappfinal.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper for parsing resultSet.
 *
 * @author David Savitsky
 * @version 1.0
 * @since 1.0
 */
public interface RowMapper<T extends Identifiable> {

    /**
     * This method receives {@code ResultSet} and parses into {@code Entity}
     *
     * @param resultSet result set which will be parsed.
     * @throws SQLException if any exception occurred during execution.
     *
     */
    T map (ResultSet resultSet) throws SQLException;

    static RowMapper<? extends Identifiable> create(String table) {
        switch (table) {
            case User.TABLE_NAME:
                return  new UserRowMapper();
            case Food.TABLE_NAME:
                return new FoodRowMapper();
            case Order.TABLE_NAME:
                return new OrderRowMapper();
            case OrdersFood.TABLE_NAME:
                return new OrdersFoodRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table = " + table);
        }
    }

}
