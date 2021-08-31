package ua.project.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.project.model.entity.Role;
import ua.project.model.entity.Ticket;
import ua.project.model.services.ExhibitionService;
import ua.project.model.services.TicketService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

public class BuyTicketCommand implements Command {
    static Logger logger = LogManager.getLogger(AddExhibitionCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        final TicketService service = new TicketService();
        final ExhibitionService exhibitionService = new ExhibitionService();

        try {
            if (request.getSession().getAttribute("role").equals(Role.ADMIN)
                    || request.getSession().getAttribute("role").equals(Role.USER)) {
                int exhibitionId = Integer.parseInt(request.getParameterNames().nextElement());
                Ticket ticket = new Ticket(request.getSession().getAttribute("email").toString(),
                        Integer.parseInt(request.getSession().getAttribute("userId").toString()),
                        exhibitionService.findById(exhibitionId).get().getTopic(),
                        exhibitionId);
                service.saveNewTicket(ticket);
                logger.info("New ticket was created");

            } else {
                logger.info("User was not logged in");
                return "WEB-INF/view/logInRequest.jsp";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            logger.info("SQL got invalid data");
        }
        catch (NullPointerException exc) {
            logger.info("User was not logged in");
            return "WEB-INF/view/logInRequest.jsp";
        }
        return "WEB-INF/view/buy.jsp";
    }
}
