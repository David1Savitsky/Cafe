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
import java.util.Map;
import java.util.Optional;

public abstract class AbstractDao <T extends Identifiable> implements Dao<T> {

    private final Connection connection;
    private final RowMapper<T> rowMapper;

    protected AbstractDao(Connection connection, RowMapper<T> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)){
            ResultSet resultSet = statement.executeQuery();
            return extractResultsFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    protected boolean executeUpdate(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<T> getAll() throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeQuery("select * from" + table, mapper);
    }

    protected Optional<T> executeForSingleResult(String query, Object... params) {
        List<T> items = null;
        try {
            items = executeQuery(query, params);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        Integer itemsSize = items.size();
        if(itemsSize == 1) {
            return Optional.of(items.get(0));
        } else if (itemsSize > 1) {
            throw new IllegalArgumentException("More than one record found");
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void save(T item) throws DaoException {
        Map<String, Object> fields = getFields(item);
        String query = item.getId() == null ? generateInsertQuery(fields) : generateUpdateQuery(fields);
        executeUpdate(query);
    }

    private List<T> extractResultsFromResultSet(ResultSet resultSet) throws SQLException {
        List<T> entities = new ArrayList<>();
        while (resultSet.next()) {
            T entity = rowMapper.map(resultSet);
            entities.add(entity);
        }
        return entities;
    }

    protected abstract Map<String, Object> getFields(T item);

    String generateInsertQuery(Map<String, Object> fields) {
        throw new UnsupportedOperationException();
    }

    String generateUpdateQuery(Map<String, Object> fields) {
        throw new UnsupportedOperationException();
    }

    protected abstract String getTableName();

}
