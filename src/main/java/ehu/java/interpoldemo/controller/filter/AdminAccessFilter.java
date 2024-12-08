package ehu.java.interpoldemo.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;


//todo

@WebFilter("/*")
public class AdminAccessFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(AdminAccessFilter.class);
    private static final String ADMIN_PARAM = "admin";
    private static final String ROLE = "role";
    private static final String ADMIN = "ADMIN";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String adminParam = httpRequest.getParameter(ADMIN_PARAM);

        if ("true".equalsIgnoreCase(adminParam)) {
            HttpSession session = httpRequest.getSession(false);
            String role = (session != null) ? (String) session.getAttribute(ROLE) : null;

            if (!ADMIN.equals(role)) {
                logger.warn("Unauthorized access to admin area. Redirecting to index.jsp");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                return;
            }
            logger.info("Admin access granted.");
        }
        chain.doFilter(request, response);
    }
}



