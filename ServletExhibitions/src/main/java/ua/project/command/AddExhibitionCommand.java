package ua.project.command;

import ua.project.configurer.Configurer;
import ua.project.model.entity.Exhibition;
import ua.project.model.entity.ExhibitionState;
import ua.project.model.services.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

public class AddExhibitionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ExhibitionService service = new ExhibitionService();
        try {
            Exhibition exhibition = new Exhibition(request.getParameter("topic"),
                    Integer.parseInt(request.getParameter("rooms")),
                    Configurer.getDateFromString(request.getParameter("startDate")),
                    Configurer.getDateFromString(request.getParameter("endDate")),
                    Configurer.getTimeFromString(request.getParameter("startTime")),
                    Configurer.getTimeFromString(request.getParameter("endTime")),
                    Integer.parseInt(request.getParameter("price")), ExhibitionState.PLANNED);
            service.saveNewExhibition(exhibition);
        } catch (SQLIntegrityConstraintViolationException ignored) {


        } catch (NullPointerException | NumberFormatException exc) {

        }
        return "WEB-INF/view/addExhibition.jsp";
    }
}
