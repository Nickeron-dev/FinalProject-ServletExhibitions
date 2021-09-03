package ua.project.servlets.listeners;

import ua.project.model.services.ExhibitionService;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @author Illia Koshkin
 */
public class PageListener implements ServletRequestListener {


    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    /**
     * This listener checks page that should be shown
     * @param servletRequestEvent event object to perform this method
     */
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        final ExhibitionService exhibitionService = new ExhibitionService();
        try {
            servletRequestEvent.getServletRequest().setAttribute("page",
                    exhibitionService.allByPage(
                            Integer.parseInt(servletRequestEvent.getServletRequest().getParameter("pageId"))));
        } catch (NullPointerException | NumberFormatException exc) {
            servletRequestEvent.getServletRequest().setAttribute("page", exhibitionService.allByPage(1));
        }
    }
}
