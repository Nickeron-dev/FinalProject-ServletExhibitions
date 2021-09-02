package ua.project.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.project.model.entity.Role;
import ua.project.model.entity.User;
import ua.project.model.services.UserService;
import ua.project.view.ITextsPaths;
import ua.project.view.View;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;

public class RegistrationCommand implements Command {
    static Logger logger = LogManager.getLogger(RegistrationCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        UserService service = new UserService();
        try {
            User user = new User(request.getParameter("username"),
                    request.getParameter("password"), request.getParameter("email"), Role.USER);
                if ( ! (Pattern.compile(View.view.getBundleText(ITextsPaths.EMAIL_REGEX)).matcher(user.getEmail()).matches()
                        && Pattern.compile(View.view.getBundleText(ITextsPaths.USERNAME_REGEX)).matcher(user.getLogin()).matches()
                        && Pattern.compile(View.view.getBundleText(ITextsPaths.PASSWORD_REGEX)).matcher(user.getPassword()).matches())) {
                    throw new IllegalArgumentException();
                }
            service.saveNewUser(user);
            logger.info("New user was saved");
            if (request.getHeader("referer").contains("registration")) {
                request.setAttribute("errorMessage", View.view.getBundleText(ITextsPaths.REGISTER_SUCCESS));
            }
        } catch (SQLIntegrityConstraintViolationException | IllegalArgumentException exc) {
            logger.info("Invalid data was inserted");
            if (request.getHeader("referer").contains("registration")) {
                request.setAttribute("errorMessage", View.view.getBundleText(ITextsPaths.INVALID_DATA));
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
