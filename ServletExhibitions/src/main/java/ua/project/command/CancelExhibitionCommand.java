package ua.project.command;

import ua.project.model.services.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

public class CancelExhibitionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        final ExhibitionService exhibitionService = new ExhibitionService();

        exhibitionService.cancelById(Integer.parseInt(request.getParameterNames().nextElement()));

        return "WEB-INF/view/cancelResult.jsp";
    }
}
