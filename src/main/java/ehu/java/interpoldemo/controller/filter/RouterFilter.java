package ehu.java.interpoldemo.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

@WebFilter("/*")
public class RouterFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(RouterFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();
        logger.info("Incoming request: Method = {}, URI = {}", method, requestURI);
        chain.doFilter(request, response);
        String resolvedPage = (String) httpRequest.getAttribute("javax.servlet.forward.request_uri");
        if (resolvedPage != null) {
            logger.info("Resolved page: {}", resolvedPage);
        } else {
            logger.info("Request completed: {}", requestURI);
        }
    }
}
