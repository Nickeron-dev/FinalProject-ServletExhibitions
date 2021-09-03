package ua.project.command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Illia Koshkin
 */
public interface Command {
    String execute(HttpServletRequest request);
}
