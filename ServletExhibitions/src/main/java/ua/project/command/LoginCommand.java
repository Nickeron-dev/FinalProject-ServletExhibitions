package ua.project.command;

import ua.project.model.entity.Role;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if ( name == null || name.equals("") || password == null || password.equals("") ) {
            return "/WEB-INF/view/login.jsp";
        }

        System.out.println(name + " " + password);

        // todo: check login with DB

        if (CommandUtility.checkUserIsLogged(request, name)) {
            return "/WEB-INF/error.jsp";
        }

        if (name.equals("admin")) {
            CommandUtility.setUserRole(request, Role.ADMIN, name);
            return "/WEB-INF/view/admin_menu.jsp";
        } else if (name.equals("user")) {
            CommandUtility.setUserRole(request, Role.USER, name);
            return "/WEB-INF/user/user_menu.jsp";
        } else {
            CommandUtility.setUserRole(request, Role.GUEST, name);
            return "/login.jsp";
        }
    }
}
