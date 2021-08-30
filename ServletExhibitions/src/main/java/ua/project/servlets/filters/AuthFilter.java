package ua.project.servlets.filters;

import ua.project.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Acidification filter.
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)

            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();
        try {
            if ((path.contains("login") &&
                    (request.getSession().getAttribute("role").equals(Role.ADMIN)
                            || request.getSession().getAttribute("role").equals(Role.USER)))
                || (path.contains("login") && request.getHeader("referer").contains("login"))) {
                request.getSession().setAttribute("role", request.getSession().getAttribute("role"));
                servletResponse.getWriter().append("You are already logged in!");
            }
        } catch (NullPointerException ignored) {

        }
        
        if(path.contains("statistics") || path.contains("addExhibition")) {
            try {
                if (request.getSession().getAttribute("role").equals(Role.ADMIN)) {
                    filterChain.doFilter(servletRequest,servletResponse);
                }else{
                    servletResponse.getWriter().append("AccessDenied");
                }
            } catch (NullPointerException ignored) {
            }

        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

}
