package com.epam.webappfinal.connection;

import com.epam.webappfinal.exception.DaoException;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final int MAXIMUM_CONNECTIONS = 10;

    private static ConnectionPool connectionPool;

    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    private Queue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> connectionsInUse;

    private static ReentrantLock connectionsLock = new ReentrantLock();

    private ConnectionPool() {
        availableConnections = new ArrayDeque<>();
        connectionsInUse = new ArrayDeque<>();
    }

    public static ConnectionPool getInstance() throws DaoException {
        ConnectionPool localConnectionPool = connectionPool;
        if (localConnectionPool == null) {
            connectionsLock.lock();
            try {
                localConnectionPool = connectionPool;
                if (localConnectionPool == null) {
                    connectionPool = localConnectionPool = new ConnectionPool();
                    connectionPool.initConnections();
                }
            } finally {
                connectionsLock.unlock();
            }
        }
        return localConnectionPool;
    }

    private void initConnections() throws DaoException {
        for (int i = 0; i < MAXIMUM_CONNECTIONS; i++) {
            ProxyConnection proxyConnection = connectionFactory.create();
            availableConnections.offer(proxyConnection);
        }
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        connectionsLock.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                availableConnections.offer(proxyConnection);
                connectionsInUse.remove(proxyConnection);
            }
        } finally {
            connectionsLock.unlock();
        }
    }

    public ProxyConnection getConnection() throws DaoException {
        connectionsLock.lock();
        ProxyConnection proxyConnection;
        try {
            proxyConnection = availableConnections.remove();
            connectionsInUse.offer(proxyConnection);
        } finally {
            connectionsLock.unlock();
        }
        return proxyConnection;
    }

}
