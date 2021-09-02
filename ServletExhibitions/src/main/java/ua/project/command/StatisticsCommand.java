package ua.project.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.project.model.entity.Exhibition;
import ua.project.model.entity.ExhibitionWithVisitorAmount;
import ua.project.model.services.ExhibitionService;
import ua.project.model.services.TicketService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Illia Koshkin
 */
public class StatisticsCommand implements Command {
    static Logger logger = LogManager.getLogger(StatisticsCommand.class);

    /**
     * This command allows to see statistics only to ADMIN
     * @param request HttpServletRequest object is necessary to complete the operation
     * @return Jsp page with all statistics
     */
    @Override
    public String execute(HttpServletRequest request) {
        final ExhibitionService exhibitionService = new ExhibitionService();
        final TicketService ticketService = new TicketService();

        List<ExhibitionWithVisitorAmount> statistics = new ArrayList<>();
        List<Exhibition> all = exhibitionService.findAll();

        all.forEach(element -> statistics.add(new ExhibitionWithVisitorAmount(element,
                (int) ticketService.countByExhibitionId(element.getId()))));
        logger.info("Statistics were taken");
        request.setAttribute("statistics", statistics);

        return "WEB-INF/view/statistics.jsp";
    }
}
