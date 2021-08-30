package ua.project.command;

import ua.project.configurer.Configurer;
import ua.project.model.entity.Exhibition;
import ua.project.model.entity.ExhibitionState;
import ua.project.model.services.ExhibitionService;
import ua.project.view.ITextsPaths;
import ua.project.view.View;

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
            if (request.getHeader("referer").contains("addExhibition")) {
                request.setAttribute("errorMessage", View.view.getBundleText(ITextsPaths.EXHIBITION_ADDING_SUCCESS));
            }
        } catch (SQLIntegrityConstraintViolationException | NumberFormatException ignored) {
            if (request.getHeader("referer").contains("addExhibition")) {
                request.setAttribute("errorMessage", View.view.getBundleText(ITextsPaths.INVALID_DATA));
            }

        } catch (NullPointerException exc) {
            try {
                request.removeAttribute("errorMessage");
            } catch (NullPointerException ignored) {

            }
        }
        return "WEB-INF/view/addExhibition.jsp";
    }
}
