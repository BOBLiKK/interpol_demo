package ehu.java.interpoldemo.controller.listener;

import ehu.java.interpoldemo.dao.connection.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger(ServletContextListenerImpl.class);

    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance();
        logger.info("Connection pool initialized successfully.");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroyPool();
        logger.info("Connection pool destroyed.");
    }
}

