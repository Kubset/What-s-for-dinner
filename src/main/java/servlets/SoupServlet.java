package servlets;

import DAO.ComponentDAO;
import Model.Component;
import Model.Soup;
import Model.SoupComponent;
import Model.Unit;
import services.SoupManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class SoupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/dashBoard.html");
        if (dispatcher != null) dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String soupName = req.getParameter("soup-name");

        String[] componentNames = req.getParameterValues("component");
        String[] count = req.getParameterValues("count");
        String[] unit = req.getParameterValues("unit");

        List<Component> components = new ArrayList<>();
        for(int i=0; i<count.length; i++) {
           components.add(new Component(componentNames[i],
                                        Integer.parseInt(count[i]),
                                        new Unit(unit[i])));
        }

        Soup soup = new Soup(soupName);
        soup.setComponents(components);

        SoupManager soupManager = new SoupManager();
        soupManager.create(soup);

        resp.sendRedirect(req.getHeader("referer"));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}

