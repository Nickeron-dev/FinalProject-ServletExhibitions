package ua.project.command;

import ua.project.model.entity.Role;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Illia Koshkin
 */
public class CommandUtility {
    /**
     * Sets GUEST role to a user
     * @param request HttpServletRequest is necessary to complete the operation
     */
    static void setGuestUserRole(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("username", "Guest");
        session.setAttribute("role", Role.GUEST);
    }

}
