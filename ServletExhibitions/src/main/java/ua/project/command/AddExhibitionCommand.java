package ua.project.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.project.configurer.Configurer;
import ua.project.model.entity.Exhibition;
import ua.project.model.entity.ExhibitionState;
import ua.project.model.services.ExhibitionService;
import ua.project.view.ITextsPaths;
import ua.project.view.View;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

public class AddExhibitionCommand implements Command {
    static Logger logger = LogManager.getLogger(AddExhibitionCommand.class);
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
            logger.info("Saved new Exhibition");
            if (request.getHeader("referer").contains("addExhibition")) {
                request.setAttribute("errorMessage", View.view.getBundleText(ITextsPaths.EXHIBITION_ADDING_SUCCESS));
            }
        } catch (SQLIntegrityConstraintViolationException | NumberFormatException ignored) {
            if (request.getHeader("referer").contains("addExhibition")) {
                request.setAttribute("errorMessage", View.view.getBundleText(ITextsPaths.INVALID_DATA));
            }
            logger.info("Page was just opened or invalid data was inserted, new Exhibition was not created");

        } catch (NullPointerException exc) {
            try {
                request.removeAttribute("errorMessage");
            } catch (NullPointerException ignored) {

            }
        }
        return "WEB-INF/view/addExhibition.jsp";
    }
}
