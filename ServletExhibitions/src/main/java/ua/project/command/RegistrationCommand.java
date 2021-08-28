package ua.project.command;

import ua.project.model.entity.Role;
import ua.project.model.entity.User;
import ua.project.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

public class RegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = new UserService();
        try {
            User user = new User(request.getParameter("username"),
                    request.getParameter("password"), request.getParameter("email"), Role.USER);
            service.saveNewUser(user);
        } catch (SQLIntegrityConstraintViolationException exc) {

            if (request.getHeader("referer").contains("registration")) {
                request.setAttribute("errorMessage", "Inserted data is invalid!");
            }

        } catch (NullPointerException exc) {
            try {
                request.removeAttribute("errorMessage");
            } catch (NullPointerException ignored) {

            }
        }

        return "/WEB-INF/view/registration.jsp";
    }
}
