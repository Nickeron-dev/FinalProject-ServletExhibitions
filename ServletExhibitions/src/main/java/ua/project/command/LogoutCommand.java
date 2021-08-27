package ua.project.command;

import ua.project.model.entity.Role;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        // todo delete current user (context and session)
        CommandUtility.setUserRole(request, Role.GUEST, "Guest");
        return "redirect:/login.jsp";
    }
}
