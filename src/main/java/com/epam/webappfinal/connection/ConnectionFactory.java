package com.epam.webappfinal.connection;

import com.epam.webappfinal.exception.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String DATABASE_PROPERTY_FILE = "database.properties";
    private static final String DATABASE_DRIVER_CLASS = "db.driver.class";
    private static final String DATABASE_CONNECTION_URL = "db.url";
    private static final String DATABASE_USER_NAME = "db.user";
    private static final String DATABASE_PASSWORD = "db.password";

    public ProxyConnection create() throws DaoException {
        Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(DATABASE_PROPERTY_FILE);
        Connection connection;
        try {
            properties.load(inputStream);
            String databaseDriver = properties.getProperty(DATABASE_DRIVER_CLASS);
            String databaseUrl = properties.getProperty(DATABASE_CONNECTION_URL);
            String databaseUser = properties.getProperty(DATABASE_USER_NAME);
            String databasePassword = properties.getProperty(DATABASE_PASSWORD);
            Class.forName(databaseDriver);
            connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new DaoException(e);
        }
        return new ProxyConnection(connection, ConnectionPool.getInstance());
    }
}

//        String url = "jdbc:mysql://localhost:3306/cafe";
//        Properties prop = new Properties();
//        prop.put("user", "root");
//        prop.put("password", "root");
//        prop.put("autoReconnect", "true");
//        prop.put("characterEncoding", "UTF-8");
//        prop.put("useUnicode", "true");
//
//        Connection cn = null;
//
//        try {
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//            cn = DriverManager.getConnection(url, prop);
//
//        } catch (SQLException e) {
//            LOGGER.info("Can't make the connection");
//            throw new DaoException("Can't make the connection");
//        }
//
//        return new ProxyConnection(cn, ConnectionPool.getInstance());

//        Connection connection = null;
//        try {
//            ResourceBundle resource = ResourceBundle.getBundle("database");
//            String url = resource.getString("url");
//            String user = resource.getString("user");
//            String pass = resource.getString("pass");
//            connection = DriverManager.getConnection(url, user, pass);
//        } catch (SQLException e) {
//            LOGGER.info("Can't make the connection");
//            throw new DaoException("Can't make the connection");
//        }
//        return (ProxyConnection) connection;
//    }

//}