package ua.project.servlets.filters;

import ua.project.model.entity.Role;
import ua.project.model.entity.User;
import ua.project.model.services.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

/**
 * Acidification filter.
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
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
        } catch (NullPointerException exc) {

        }
        
        if(path.contains("statistics") || path.contains("addExhibition")) {
            try {
                if (request.getSession().getAttribute("role").equals(Role.ADMIN)) {
                    filterChain.doFilter(servletRequest,servletResponse);
                }else{
                    servletResponse.getWriter().append("AccessDenied");
                    return;
                }
            } catch (NullPointerException ignored) {
                ignored.printStackTrace();
            }

        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

}
