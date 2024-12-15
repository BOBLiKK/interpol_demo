package ehu.java.interpoldemo.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.Locale;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;

@WebFilter("/*")
public class LocaleFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(LocaleFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String langParam = httpRequest.getParameter(LANG);
        if (langParam != null && !langParam.equals(session.getAttribute(LOCALE))) {
            session.setAttribute(LOCALE, langParam);
        }
        if (session.getAttribute(LOCALE) == null) {
            session.setAttribute(LOCALE, Locale.ENGLISH.getLanguage());
        }
        chain.doFilter(request, response);
    }
}



