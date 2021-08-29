package ua.project.command;

import ua.project.configurer.Configurer;
import ua.project.model.entity.Exhibition;
import ua.project.model.entity.ExhibitionState;
import ua.project.model.services.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.Base64;

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
        } catch (SQLIntegrityConstraintViolationException exc) {


        } catch (NullPointerException | NumberFormatException exc) {

        }
        return "WEB-INF/view/addExhibition.jsp";
    }
}
