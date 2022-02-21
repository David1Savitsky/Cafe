package com.epam.webappfinal.connection;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static ConnectionPool connectionPool;

    private static ConnectionFactory connectionFactory = new ConnectionFactory();

    private Queue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> connectionsInUse;

    private static ReentrantLock connectionsLock = new ReentrantLock();

    private ConnectionPool() {
        availableConnections = new ArrayDeque<>();
        connectionsInUse = new ArrayDeque<>();
    }

    public static ConnectionPool getInstance() {
        ConnectionPool localConnectionPool = connectionPool;
        if (localConnectionPool == null) {
            connectionsLock.lock();
            try {
                localConnectionPool = connectionPool;
                if (localConnectionPool == null) {
                    connectionPool = localConnectionPool = new ConnectionPool();
                }

                //IMPLEMENT!!!
                //initializeAvailableConnections();
                //initializeconnectionsInUse();

                connectionFactory.create();

            } finally {
                connectionsLock.unlock();
            }
        }
        return localConnectionPool;
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        connectionsLock.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                availableConnections.offer(proxyConnection);
            }
        } finally {
            connectionsLock.unlock();
        }
    }

    public ProxyConnection getConnection() {
        //start with lock
        //get from  availableConnections
        ConnectionFactory connectionFactory = new ConnectionFactory();
        return connectionFactory.create();
    }

}
