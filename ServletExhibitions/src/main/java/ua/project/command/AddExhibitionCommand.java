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
            request.getSession().setAttribute("justFromExhibition", "true");
            service.saveNewExhibition(exhibition);
            request.removeAttribute("justFromExhibition");
        } catch (SQLIntegrityConstraintViolationException exc) {
            System.out.println("Added");
            if (request.getHeader("referer").contains("addExhibition")) {
                request.setAttribute("errorMessage", "Inserted data is invalid!");
            }

        } catch (NullPointerException | NumberFormatException exc) {
            try {
                System.out.println("Here");
                request.getSession().getAttribute("justFromExhibition").equals("true");
                request.setAttribute("errorMessage", "Inserted data is invalid!");
            } catch (NullPointerException e) {
                try {
                    request.removeAttribute("errorMessage");
                } catch (NullPointerException ignored) {
                    request.removeAttribute("justFromExhibition");
                }
            }
        }
        return "WEB-INF/view/addExhibition.jsp";
    }
}
