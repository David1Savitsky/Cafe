package com.epam.webappfinal.dao;

import com.epam.webappfinal.connection.ConnectionPool;
import com.epam.webappfinal.exception.DaoException;

public class DaoHelperFactory {

    public DaoHelper create() throws DaoException {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
