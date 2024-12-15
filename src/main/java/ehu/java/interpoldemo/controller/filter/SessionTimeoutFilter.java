package ehu.java.interpoldemo.controller.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static ehu.java.interpoldemo.constants.PageNameConstant.*;
import java.io.IOException;

@WebFilter("/*")
public class SessionTimeoutFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(SessionTimeoutFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        if(session == null) {
            request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
        }else{
            chain.doFilter(request, response);
        }
    }
}

