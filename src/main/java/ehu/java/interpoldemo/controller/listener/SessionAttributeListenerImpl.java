package ehu.java.interpoldemo.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//todo

@WebListener
public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {

    private static final Logger logger = LogManager.getLogger(SessionAttributeListenerImpl.class);

    public void attributeAdded(final HttpSessionBindingEvent event) {
        logger.info("Attribute added to session: {} = {}", event.getName(), event.getValue());
    }
    public void attributeRemoved(final HttpSessionBindingEvent event) {
        logger.info("Attribute removed from session: {}", event.getName());
    }
    public void attributeReplaced(final HttpSessionBindingEvent event) {
        logger.info("Attribute replaced in session: {} = {}", event.getName(), event.getValue());
    }
}
