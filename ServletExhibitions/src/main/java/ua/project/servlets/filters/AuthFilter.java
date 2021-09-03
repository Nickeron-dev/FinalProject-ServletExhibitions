package ua.project.servlets.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.project.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Illia Koshkin
 */
public class AuthFilter implements Filter {
    static Logger logger = LogManager.getLogger(AuthFilter.class);
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * This method checks user's role and gives or refuses to give an access to a certain page
     * @param servletRequest ServletRequest object to check session, URI etc.
     * @param servletResponse ServletResponse object to show short page with message
     * @param filterChain to continue filtering chain
     * @throws IOException in case of I/O issue with showing short message page
     * @throws ServletException in case of issue with request or response
     */
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
                    logger.info("Admin opened statistics/addExhibition page");
                    filterChain.doFilter(servletRequest,servletResponse);
                }else{
                    logger.info("User tried to access statistics/addExhibition page without permission");
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
