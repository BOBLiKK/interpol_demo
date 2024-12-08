package ehu.java.interpoldemo.controller.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = {"/controller"}, filterName = "XssFilter")
public class XssFilter implements Filter {
    private static final String XSS_ATTACK_REGEX = "[<>/]";
    private static final String EMPTY_STRING = "";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        if (!(servletRequest instanceof HttpServletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletRequestWrapper safeRequest = new CustomHttpServletRequest((HttpServletRequest) servletRequest);

        filterChain.doFilter(safeRequest, servletResponse);
    }

    private static class CustomHttpServletRequest extends HttpServletRequestWrapper {
        private final Map<String, String[]> sanitizedParameters;

        public CustomHttpServletRequest(HttpServletRequest request) {
            super(request);
            sanitizedParameters = new HashMap<>();

            request.getParameterMap().forEach((key, values) -> {
                String[] sanitizedValues = sanitizeValues(values);
                sanitizedParameters.put(key, sanitizedValues);
            });
        }

        @Override
        public String getParameter(String name) {
            String[] values = sanitizedParameters.get(name);
            return (values != null && values.length > 0) ? values[0] : null;
        }

        @Override
        public String[] getParameterValues(String name) {
            return sanitizedParameters.get(name);
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return Collections.unmodifiableMap(sanitizedParameters);
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return Collections.enumeration(sanitizedParameters.keySet());
        }

        private String[] sanitizeValues(String[] values) {
            String[] sanitized = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                sanitized[i] = values[i].replaceAll(XSS_ATTACK_REGEX, EMPTY_STRING);
            }
            return sanitized;
        }
    }
}
