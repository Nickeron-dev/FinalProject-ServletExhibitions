package ua.project.command;

import ua.project.model.entity.Role;
import ua.project.model.entity.Ticket;
import ua.project.model.services.ExhibitionService;
import ua.project.model.services.TicketService;
import ua.project.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

public class BuyTicketCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        final TicketService service = new TicketService();
        final ExhibitionService exhibitionService = new ExhibitionService();

        try {
            if (request.getSession().getAttribute("role").equals(Role.ADMIN)
                    || request.getSession().getAttribute("role").equals(Role.USER)) {
                Integer exhibitionId = Integer.parseInt(request.getParameterNames().nextElement());
                Ticket ticket = new Ticket(request.getSession().getAttribute("email").toString(),
                        Integer.parseInt(request.getSession().getAttribute("userId").toString()),
                        exhibitionService.findById(exhibitionId).get().getTopic(),
                        exhibitionId);
                service.saveNewTicket(ticket);

            } else {
                return "WEB-INF/view/logInRequest.jsp";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        catch (NullPointerException exc) {
            exc.printStackTrace();
            System.out.println("caught 1");
            try {
                if (request.getSession().getAttribute("role").equals(Role.ADMIN)
                        || request.getSession().getAttribute("role").equals(Role.USER)) {

                }
            } catch (NullPointerException ex) {
                return "WEB-INF/view/logInRequest.jsp";
            }
        }
        return "WEB-INF/view/buy.jsp";
    }
}
