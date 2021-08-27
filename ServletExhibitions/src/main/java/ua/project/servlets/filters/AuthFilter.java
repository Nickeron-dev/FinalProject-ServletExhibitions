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
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final UserService userService = new UserService();

        final HttpSession session = req.getSession();

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {

            final Role role = (Role) session.getAttribute("role");

            moveToMenu(req, res, role);


        } else if (userService.login(req.getParameter("login")).isPresent()) {
            User user = userService.login(req.getParameter("login")).get();
            final Role role = user.getRole();

            req.getSession().setAttribute("password", user.getPassword());
            req.getSession().setAttribute("login", user.getLogin());
            req.getSession().setAttribute("role", user.getRole());

            moveToMenu(req, res, role);

        } else {

            moveToMenu(req, res, Role.GUEST);
        }
    }

    /**
     * Move user to menu.
     * If access 'admin' move to admin menu.
     * If access 'user' move to user menu.
     */
    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final Role role)
            throws ServletException, IOException {


        if (role.equals(Role.ADMIN)) {

            req.getRequestDispatcher("/WEB-INF/view/admin_menu.jsp").forward(req, res);

        } else if (role.equals(Role.USER)) {

            req.getRequestDispatcher("/WEB-INF/view/user_menu.jsp").forward(req, res);

        } else {

            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
        }
    }


    @Override
    public void destroy() {
    }

}
