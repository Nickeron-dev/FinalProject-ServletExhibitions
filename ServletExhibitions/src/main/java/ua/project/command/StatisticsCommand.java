package ua.project.command;

import ua.project.model.entity.Exhibition;
import ua.project.model.entity.ExhibitionWithVisitorAmount;
import ua.project.model.services.ExhibitionService;
import ua.project.model.services.TicketService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class StatisticsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        final ExhibitionService exhibitionService = new ExhibitionService();
        final TicketService ticketService = new TicketService();

        List<ExhibitionWithVisitorAmount> statistics = new ArrayList<>();
        List<Exhibition> all = exhibitionService.findAll();
        System.out.println("All materials taken");
        System.out.println("statistics empty: " + statistics);

        all.forEach(element -> statistics.add(new ExhibitionWithVisitorAmount(element,
                (int) ticketService.countByExhibitionId(element.getId()))));
        request.setAttribute("statistics", statistics);
        System.out.println("statistics attribute was set");
        System.out.println(statistics);

        return "WEB-INF/view/statistics.jsp";
    }
}
