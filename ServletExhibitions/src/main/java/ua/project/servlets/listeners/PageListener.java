package ua.project.servlets.listeners;

import ua.project.model.services.ExhibitionService;
import ua.project.view.ILocaleNames;
import ua.project.view.View;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import java.util.Locale;
import java.util.Optional;

public class PageListener implements ServletRequestListener {


    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

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
