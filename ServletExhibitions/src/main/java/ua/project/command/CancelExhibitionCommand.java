package ua.project.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.project.model.services.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Illia Koshkin
 */
public class CancelExhibitionCommand implements Command {
    static Logger logger = LogManager.getLogger(CancelExhibitionCommand.class);

    /**
     * This command cancels an exhibition
     * @param request HttpServletRequest object is necessary to complete the operation
     * @return jsp page with the result of an operation
     */
    @Override
    public String execute(HttpServletRequest request) {
        final ExhibitionService exhibitionService = new ExhibitionService();

        exhibitionService.cancelById(Integer.parseInt(request.getParameterNames().nextElement()));
        logger.info("Exhibition was canceled");
        return "WEB-INF/view/cancelResult.jsp";
    }
}
