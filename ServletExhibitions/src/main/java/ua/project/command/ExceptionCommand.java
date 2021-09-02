package ua.project.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Illia Koshkin
 */
public class ExceptionCommand implements Command{
    static Logger logger = LogManager.getLogger(ExceptionCommand.class);

    /**
     * Shows exception jsp
     * @param request HttpServletRequest object is necessary to complete the operation
     * @return Jsp page with error message
     */
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Error page was shown");
        return "WEB-INF/error.jsp";
    }
}
