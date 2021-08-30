package ua.project.servlets.listeners;

import ua.project.view.ILocaleNames;
import ua.project.view.View;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import java.util.Locale;
import java.util.Optional;

public class LocalizationListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        try {
            if (servletRequestEvent.getServletRequest().getParameter("ukr").equals("UKR")) {
                View.view.changeLocale(Optional.of(new Locale(ILocaleNames.UKR_LANGUAGE, ILocaleNames.UKR_COUNTRY)));
            }
        } catch (NullPointerException ignored) {

        }
        try {
            if (servletRequestEvent.getServletRequest().getParameter("eng").equals("ENG")) {
                View.view.changeLocale(Optional.of(new Locale(ILocaleNames.DEFAULT_LANGUAGE)));
            }
        } catch (NullPointerException ignored) {
        }
    }
}
