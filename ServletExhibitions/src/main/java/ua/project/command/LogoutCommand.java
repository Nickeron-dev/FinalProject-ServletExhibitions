package ua.project.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Illia Koshkin
 */
public class LogoutCommand implements Command {
    static Logger logger = LogManager.getLogger(LogoutCommand.class);

    /**
     * Logs user out
     * @param request HttpServletRequest object is necessary to complete the operation
     * @return Home jsp page with logged out user
     */
    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");
        session.removeAttribute("userId");
        session.removeAttribute("email");;
        CommandUtility.setGuestUserRole(request);
        logger.info("User logged out");
        return  "/WEB-INF/index.jsp";
    }
}
