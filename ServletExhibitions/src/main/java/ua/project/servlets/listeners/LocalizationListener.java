package ua.project.servlets.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.project.command.AddExhibitionCommand;
import ua.project.view.ILocaleNames;
import ua.project.view.View;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import java.util.Locale;
import java.util.Optional;

public class LocalizationListener implements ServletRequestListener {
    static Logger logger = LogManager.getLogger(LocalizationListener.class);
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        try {
            if (servletRequestEvent.getServletRequest().getParameter("ukr").equals("UKR")) {
                View.view.changeLocale(Optional.of(new Locale(ILocaleNames.UKR_LANGUAGE, ILocaleNames.UKR_COUNTRY)));
                logger.info("Language was changed to: " + ILocaleNames.UKR_LANGUAGE);
            }
        } catch (NullPointerException ignored) {

        }
        try {
            if (servletRequestEvent.getServletRequest().getParameter("eng").equals("ENG")) {
                View.view.changeLocale(Optional.of(new Locale(ILocaleNames.DEFAULT_LANGUAGE)));
                logger.info("Language was changed to: " + ILocaleNames.DEFAULT_LANGUAGE);
            }
        } catch (NullPointerException ignored) {
        }
    }
}
