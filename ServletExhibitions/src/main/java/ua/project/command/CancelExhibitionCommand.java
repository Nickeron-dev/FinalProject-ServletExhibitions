package ua.project.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.project.model.services.ExhibitionService;

import javax.servlet.http.HttpServletRequest;

public class CancelExhibitionCommand implements Command {
    static Logger logger = LogManager.getLogger(CancelExhibitionCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        final ExhibitionService exhibitionService = new ExhibitionService();

        exhibitionService.cancelById(Integer.parseInt(request.getParameterNames().nextElement()));
        logger.info("Exhibition was canceled");
        return "WEB-INF/view/cancelResult.jsp";
    }
}
