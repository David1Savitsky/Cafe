package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Identifiable;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.mapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao <T extends Identifiable> implements Dao<T> {

    private final Connection connection;

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected List<T> executeQuery(String query, RowMapper<T> mapper, Object... params) throws DaoException {

        try (PreparedStatement statement = createStatement(query, params)){
            ResultSet resultSet = statement.executeQuery(query);
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity  = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement =connection.prepareStatement(query);
        for (int i = 1; i < params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    public List<T> getAll() throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeQuery("select * from" + table, mapper);
    }

    protected Optional<T> executeForSingleResult(String query, RowMapper<T> mapper, Object... params) throws DaoException {

        List<T> items = executeQuery(query, mapper, params);
        Integer itemsSize = items.size();
        if(itemsSize == 1) {
            return Optional.of(items.get(0));
        } else if (itemsSize > 1) {
            throw new IllegalArgumentException("More than one record found");
        } else {
            return Optional.empty();
        }

    }

    protected abstract String getTableName();

}
