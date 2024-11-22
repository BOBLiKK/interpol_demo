package ehu.java.interpoldemo.dao.connection;

import ehu.java.interpoldemo.dao.impl.UserDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static final int NUMBER_OF_CONNECTIONS = 8;
    private BlockingQueue<Connection> freeConnections = new LinkedBlockingQueue<>(NUMBER_OF_CONNECTIONS);
    private BlockingQueue<Connection> usedConnections = new LinkedBlockingQueue<>(NUMBER_OF_CONNECTIONS);
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/Interpol";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "Kukaracha336";
    private static Properties prop = new Properties();
    private static CountDownLatch latch = new CountDownLatch(1);
    private static AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    static {
        prop.put("user", DATABASE_USER);
        prop.put("password", DATABASE_PASSWORD);
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        prop.put("useSSL", "true");
        prop.put("useJDBCCompliantTimezoneShift", "true");
        prop.put("useLegacyDatetimeCode", "false");
        prop.put("serverTimezone", "UTC");
        prop.put("serverSslCert", "classpath:server.crt");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.fatal("Database driver not found", e);
            throw new ExceptionInInitializerError("Database driver not found");
        }
    }

    public static ConnectionPool getInstance() throws SQLException {
        if (instance == null) {
            if (isInitialized.compareAndSet(false, true)) {
                try{
                    instance = new ConnectionPool();
                } finally{
                    latch.countDown();
                }
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                logger.error("Thread interrupted while awaiting ConnectionPool initialization.", e);
                throw new SQLException(e);
            }
        }
        return instance;
    }

    private ConnectionPool() {
        try {
            for (int i = 0; i < NUMBER_OF_CONNECTIONS; i++) {
                Connection connection = DriverManager.getConnection(DATABASE_URL, prop);
                freeConnections.add(connection);
            }
        } catch (SQLException e) {
            throw new ExceptionInInitializerError("Initialization failed: " + e.getMessage());
        }
    }


    public Connection getConnection() throws SQLException {
        Connection connection;
        try {
            connection = freeConnections.poll(10, TimeUnit.SECONDS);
            if (connection == null) {
                logger.error("Timeout while waiting for a connection from the pool.");
                throw new SQLException("Timeout while waiting for a connection from the pool.");
            }
            usedConnections.put(connection);
        } catch (InterruptedException  e) {
            logger.fatal("Thread interrupted while attempting to take a connection from pool.", e);
            Thread.currentThread().interrupt();
            throw new SQLException("Thread was interrupted while waiting for a connection.", e);
        }
        return connection;
    }


    //todo
    public void releaseConnection(Connection connection) {
        try {
            usedConnections.remove(connection);
            freeConnections.put(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //todo
    public void destroyPool() {
        for (int i = 0; i < NUMBER_OF_CONNECTIONS; i++) {
            try {
                Connection connection = freeConnections.take();
                connection.close();
            } catch (SQLException | InterruptedException e) {
                logger.error("Error taking connection: " + e.getMessage());
            } finally {
                deregisterDrivers();
            }
        }
    }


    //todo
    private void deregisterDrivers() {
        DriverManager.drivers().forEach(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Failed to deregister driver due to an SQL exception. " + e.getMessage());
            }
        });
    }
}