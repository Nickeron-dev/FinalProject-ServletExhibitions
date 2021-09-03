package ua.project.servlets.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Illia Koshkin
 */
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    /**
     * This filter sets proper encodings
     * @param servletRequest ServletResponse object to set content type and encoding
     * @param servletResponse ServletRequest object to set encoding
     * @param filterChain to continue filtering chain
     * @throws IOException in case of I/O issue
     * @throws ServletException in case of issue with request or response
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
