package ua.project.servlets.servlet;

import ua.project.command.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<String>());

        commands.put("logout", new LogoutCommand());
        commands.put("login", new LoginCommand());
        commands.put("error", new ExceptionCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("addExhibition", new AddExhibitionCommand());
        commands.put("buy", new BuyTicketCommand());
        commands.put("statistics", new StatisticsCommand());
        commands.put("cancel", new CancelExhibitionCommand());
        commands.put("plan", new PlanExhibitionCommand());
        commands.put("filter", new FilterByDateCommand());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/", "");
        Command command = commands.getOrDefault(path, (defaultPath) -> "/index.jsp");
        System.out.println(command.getClass().getName());
        String page = command.execute(request);

        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
