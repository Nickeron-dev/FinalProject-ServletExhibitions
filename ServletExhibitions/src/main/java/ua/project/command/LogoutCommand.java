package ua.project.command;

import ua.project.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");
        CommandUtility.setUserRole(request, Role.GUEST, "Guest");
        System.out.println(request.getContextPath());
        return  "/WEB-INF/index.jsp";
    }
}
