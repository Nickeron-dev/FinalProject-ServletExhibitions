package ua.project.command;

import ua.project.model.entity.Role;
import ua.project.model.entity.User;
import ua.project.model.services.UserService;
import ua.project.view.ITextsPaths;
import ua.project.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static java.util.Objects.nonNull;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        final UserService userService = new UserService();
        Role role = Role.GUEST;
        final HttpSession session = request.getSession();
        try {
            request.removeAttribute("errorMessage");
        } catch (NullPointerException ignored) {

        }
        try {
            if ((session.getAttribute("role").equals(Role.ADMIN)
                            || session.getAttribute("role").equals(Role.USER))
                    && request.getHeader("referer").contains("login")) {
                return "/WEB-INF/index.jsp";
            }
        } catch (NullPointerException ignored) {

        }
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {

            role = (Role) session.getAttribute("role");

        } else if (userService.login(request.getParameter("login")).isPresent()) {
            User user = userService.login(request.getParameter("login")).get();
            if (user.getPassword().equals(request.getParameter("password"))) {
                role = user.getRole();

                request.getSession().setAttribute("password", user.getPassword());
                request.getSession().setAttribute("login", user.getLogin());
                request.getSession().setAttribute("userId", user.getId());
                request.getSession().setAttribute("email", user.getEmail());
                request.getSession().setAttribute("role", user.getRole());
            } else {
                if (request.getHeader("referer").contains("login")) {
                    request.setAttribute("errorMessage", View.view.getBundleText(ITextsPaths.INVALID_DATA));
                }
            }
        } else {
            if (request.getHeader("referer").contains("login")) {
                request.setAttribute("errorMessage", View.view.getBundleText(ITextsPaths.INVALID_DATA));
            }
        }
        if (role.equals(Role.ADMIN)) {

            return "/WEB-INF/view/admin_menu.jsp";

        } else if (role.equals(Role.USER)) {

            return "/WEB-INF/view/user_menu.jsp";

        } else {

            return "/WEB-INF/view/login.jsp";
        }
    }
}
