package ua.project.servlet;

import ua.project.view.ITextsPaths;
import ua.project.view.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class GetIndexPageServlet extends HttpServlet {

    private static String index = "/WEB-INF/index.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("hello", View.view.getBundleText(ITextsPaths.HOME));
        request.getRequestDispatcher(index).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {
        request.setCharacterEncoding("UTF8");

        System.out.println(request.getParameter("UKR"));
        System.out.println(request.getParameter("EN"));
        System.out.println(request.getParameter("en"));
        System.out.println(request.getParameter("ukr"));
        System.out.println("HEllo");
        log("Hello");

        log(request.getParameter("UKR"));
        log(request.getParameter("EN"));
        log(request.getParameter("en"));
        log(request.getParameter("ukr"));
        doGet(request, response);
    }
}
