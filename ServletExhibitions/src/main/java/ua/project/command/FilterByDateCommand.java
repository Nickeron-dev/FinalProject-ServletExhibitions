package ua.project.command;

import ua.project.configurer.Configurer;
import ua.project.model.services.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class FilterByDateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        ExhibitionService exhibitionService = new ExhibitionService();

        LocalDate date = Configurer.getDateFromString(request.getParameter("filterDate"));
        request.setAttribute("filteredList", exhibitionService.filterByDate(date));
        return "WEB-INF/view/filterByDate.jsp";
    }
}
