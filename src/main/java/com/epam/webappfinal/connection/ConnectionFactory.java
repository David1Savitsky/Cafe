package com.epam.webappfinal.connection;

import com.epam.webappfinal.exception.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String PROPERTY_FILE = "database.properties";
    private static final String DRIVER_CLASS = "db.driver.class";
    private static final String CONNECTION_URL = "db.url";
    private static final String USER_NAME = "db.user";
    private static final String PASSWORD = "db.password";

    public ProxyConnection create() throws DaoException {
        Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE);
        Connection connection;
        try {
            properties.load(inputStream);
            String databaseDriver = properties.getProperty(DRIVER_CLASS);
            String databaseUrl = properties.getProperty(CONNECTION_URL);
            String databaseUser = properties.getProperty(USER_NAME);
            String databasePassword = properties.getProperty(PASSWORD);
            Class.forName(databaseDriver);
            connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new DaoException(e);
        }
        return new ProxyConnection(connection, ConnectionPool.getInstance());
    }
}