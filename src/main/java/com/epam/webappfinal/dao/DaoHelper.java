package com.epam.webappfinal.dao;

import com.epam.webappfinal.connection.ConnectionPool;
import com.epam.webappfinal.connection.ProxyConnection;
import com.epam.webappfinal.exception.DaoException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) {
        this.connection = pool.getConnection();
    }

    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }

    public FoodDao createFoodDao() {
        return new FoodDaoImpl(connection);
    }

    public OrderDao createOrderDao() {
        return new OrderDaoImpl(connection);
    }

    public OrdersFoodDao createOrdersFoodDao() {
        return new OrdersFoodDaoImpl(connection);
    }

//    public OrderDao createUserDao() {
//        return new OrderDao(connection);
//    }

    @Override
    public void close() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void  endTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            new DaoException(e);
        }
    }
}
