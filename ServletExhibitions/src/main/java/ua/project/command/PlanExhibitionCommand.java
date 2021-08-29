package ua.project.command;

import ua.project.model.services.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

public class PlanExhibitionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        final ExhibitionService exhibitionService = new ExhibitionService();

        exhibitionService.planById(Integer.parseInt(request.getParameterNames().nextElement()));

        return "WEB-INF/view/planResult.jsp";
    }
}
