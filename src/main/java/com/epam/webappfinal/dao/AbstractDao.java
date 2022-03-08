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

    private static final String GET_BY_ID_QUERY = "SELECT * FROM %s WHERE id = ? ;";
    private static final String REMOVE_BY_ID_QUERY = "DELETE FROM %s WHERE id = ? ;";

    private final Connection connection;
    private final RowMapper<T> rowMapper;
    private final String tableName;

    public AbstractDao(Connection connection, RowMapper<T> rowMapper, String tableName) {
        this.connection = connection;
        this.rowMapper = rowMapper;
        this.tableName = tableName;
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

    private List<T> extractResultsFromResultSet(ResultSet resultSet) throws SQLException {
        List<T> entities = new ArrayList<>();
        while (resultSet.next()) {
            T entity = rowMapper.map(resultSet);
            entities.add(entity);
        }
        return entities;
    }

    protected void executeUpdate(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected Optional<T> executeForSingleResult(String query, Object... params) throws DaoException {
        List<T> items = null;

        items = executeQuery(query, params);
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

    @Override
    public List<T> getAll() throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeQuery("select * from " + table, mapper);
    }

    @Override
    public Optional<T> getById(Long id) throws DaoException {
        String query = String.format(GET_BY_ID_QUERY, tableName);
        return executeForSingleResult(query, id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        String query = String.format(REMOVE_BY_ID_QUERY, tableName);
        executeUpdate(query, id);
    }

    public String generateInsertQuery(Map<String, Object> fields) {
        throw new UnsupportedOperationException();
    }

    public String generateUpdateQuery(Map<String, Object> fields) {
        throw new UnsupportedOperationException();
    }

    protected String getTableName() {
        return tableName;
    }


    protected abstract Map<String, Object> getFields(T item);
}
