package ehu.java.interpoldemo.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {

    private static final Logger logger = LogManager.getLogger(SessionAttributeListenerImpl.class);

    public void attributeAdded(final HttpSessionBindingEvent event) {
        logger.info("Attribute added to session: {} = {}", event.getName(), event.getValue());
    }
    public void attributeRemoved(final HttpSessionBindingEvent event) {
        logger.info("Attribute '{}' with value '{}' removed. Session ID: {}", event.getName(), event.getValue(), event.getSession().getId());
    }
}
