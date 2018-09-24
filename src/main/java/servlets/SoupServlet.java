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
        String[] URL = req.getRequestURI().toString().split("/");
        if(URL[2].equals("edit")) {
            doPut(req,resp);
        } else {
            String soupName = req.getParameter("soup-name");

            String[] componentNames = req.getParameterValues("component");
            String[] count = req.getParameterValues("count");
            String[] unit = req.getParameterValues("unit");
            String recipe = req.getParameterValues("recipe")[0];

            List<Component> components = new ArrayList<>();
            for (int i = 0; i < count.length; i++) {
                components.add(new Component(componentNames[i],
                        Integer.parseInt(count[i]),
                        new Unit(unit[i])));
            }

            Soup soup = new Soup(soupName);
            soup.setComponents(components);
            soup.setRecipe(recipe);

            SoupManager soupManager = new SoupManager();
            soupManager.create(soup);

            resp.sendRedirect(req.getHeader("referer"));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int soupId = Integer.parseInt(req.getParameter("id"));
        String soupName = req.getParameter("soup-name");
        String[] componentNames = req.getParameterValues("component");
        String[] count = req.getParameterValues("count");
        String[] unit = req.getParameterValues("unit");
        String recipe = req.getParameter("recipe");

        List<Component> components = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            components.add(new Component(componentNames[i],
                    Integer.parseInt(count[i]),
                    new Unit(unit[i])));
        }

        Soup soup = new Soup(soupId,soupName,components);
        soup.setRecipe(recipe);
        SoupManager soupManager = new SoupManager();
        soupManager.edit(soup);

        resp.sendRedirect(req.getHeader("referer"));
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}

