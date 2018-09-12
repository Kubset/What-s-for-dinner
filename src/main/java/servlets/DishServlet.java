package servlets;

import Model.Component;
import Model.MainDish;
import Model.Soup;
import Model.Unit;
import services.DishManager;
import services.SoupManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DishServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/dashBoard.html");
        if (dispatcher != null) dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dishName = req.getParameter("dish-name");

        String[] componentNames = req.getParameterValues("component");
        String[] count = req.getParameterValues("count");
        String[] unit = req.getParameterValues("unit");

        List<Component> components = new ArrayList<>();
        for(int i=0; i<count.length; i++) {
            components.add(new Component(componentNames[i],
                    Integer.parseInt(count[i]),
                    new Unit(unit[i])));
        }

        MainDish mainDish = new MainDish(dishName);
        mainDish.setComponents(components);

        DishManager dishManager = new DishManager();

        dishManager.create(mainDish);

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

