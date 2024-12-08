package ehu.java.interpoldemo.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;


@WebListener
public class SecurityListenerImpl implements HttpSessionListener, HttpSessionAttributeListener {

    private static final Logger logger = LogManager.getLogger(SecurityListenerImpl.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (USER.equals(event.getName())) {
            logger.info("User '{}' logged in. Session ID: {}", event.getValue(), event.getSession().getId());
        } else if (NEW_USER.equals(event.getName())) {
            logger.info("New user '{}' registered. Session ID: {}", event.getValue(), event.getSession().getId());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        logger.info("User '{}' logged out. Session ID: {}", event.getValue(), event.getSession().getId());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if (USER.equals(event.getName())) {
            logger.info("User '{}' session updated. Session ID: {}", event.getValue(), event.getSession().getId());
        }
    }
}
