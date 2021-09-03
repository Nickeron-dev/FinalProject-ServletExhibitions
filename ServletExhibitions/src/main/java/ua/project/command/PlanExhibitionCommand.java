package ua.project.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.project.model.services.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Illia Koshkin
 */
public class PlanExhibitionCommand implements Command {
    static Logger logger = LogManager.getLogger(PlanExhibitionCommand.class);

    /**
     * Accomplishes planning an exhibition
     * @param request HttpServletRequest object is necessary to complete the operation
     * @return Jsp page with the result of the operation
     */
    @Override
    public String execute(HttpServletRequest request) {
        final ExhibitionService exhibitionService = new ExhibitionService();

        exhibitionService.planById(Integer.parseInt(request.getParameterNames().nextElement()));
        logger.info("Exhibition was planned");
        return "WEB-INF/view/planResult.jsp";
    }
}
