package ua.project.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.project.configurer.Configurer;
import ua.project.model.services.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 * @author Illia Koshkin
 */
public class FilterByDateCommand implements Command {
    static Logger logger = LogManager.getLogger(FilterByDateCommand.class);

    /**
     * Filters all exhibitions by given date
     * @param request HttpServletRequest object is necessary to complete the operation
     * @return Jsp page with filtered exhibitions
     */
    @Override
    public String execute(HttpServletRequest request) {

        ExhibitionService exhibitionService = new ExhibitionService();

        LocalDate date = Configurer.getDateFromString(request.getParameter("filterDate"));
        request.setAttribute("filteredList", exhibitionService.filterByDate(date));
        logger.info("Exhibitions were filtered by date: " + date);
        return "WEB-INF/view/filterByDate.jsp";
    }
}
