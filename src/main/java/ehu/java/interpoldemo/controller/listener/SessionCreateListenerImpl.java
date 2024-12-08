package ehu.java.interpoldemo.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionCreateListenerImpl implements HttpSessionListener{

    private static final Logger logger = LogManager.getLogger(SessionCreateListenerImpl.class);

    public void sessionCreated(HttpSessionEvent se){
        logger.info("Session created: {}", se.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent se){
        logger.info("Session destroyed: {}", se.getSession().getId());
    }
}
