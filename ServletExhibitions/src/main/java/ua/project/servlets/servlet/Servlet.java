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

/**
 * @author Illia Koshkin
 */
public class Servlet extends HttpServlet {
    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Initialization method of the main servlet
     * @param servletConfig object that is necessary to get context
     */
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

    /**
     * Get method for Http GET method
     * @param request object that is used to call required method
     * @param response object that is used to call required method
     * @throws IOException in case of I/O issue
     * @throws ServletException in case of a problem with request and response
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    /**
     * Post method for HTTP POST method
     * @param request object that is used to call required method
     * @param response object that is used to call required method
     * @throws IOException in case of I/O issue
     * @throws ServletException in case of a problem with request and response
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    /**
     * Processing request method
     * @param request object is used check which page is desirable
     * @param response object is used to redirect a user
     * @throws IOException in case of I/O issue
     * @throws ServletException in case of a problem with request and response
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/", "");
        Command command = commands.getOrDefault(path, (defaultPath) -> "/index.jsp");
        String page = command.execute(request);

        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
