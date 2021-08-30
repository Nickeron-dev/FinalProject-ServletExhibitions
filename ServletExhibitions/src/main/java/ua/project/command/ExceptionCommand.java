package ua.project.command;

import javax.servlet.http.HttpServletRequest;

public class ExceptionCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "WEB-INF/error.jsp";

    }
}
