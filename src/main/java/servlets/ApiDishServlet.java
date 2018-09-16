package servlets;

import Criteria.AllDishes;
import Criteria.AllSoups;
import Criteria.SqlCriteria;
import DAO.MainDishDAO;
import DAO.SoupDAO;
import Mappers.MainDishMapper;
import Mappers.Mapper;
import Mappers.SoupMapper;
import Model.MainDish;
import Model.Soup;
import services.DishManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ApiDishServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] URL = req.getRequestURI().toString().split("/");
        Mapper<MainDish> mapper = new MainDishMapper();
        DishManager dishManager = new DishManager();
        String json = null;

        if(URL.length == 5 && URL[3].equals("random")) {
            //TODO: not implemented yet

        } else if(URL.length == 4) {
            MainDish mainDish = dishManager.get(Integer.parseInt(URL[3]));
            json = mapper.mapToJson(mainDish);
        } else {
            List<MainDish> soups = dishManager.getAll();
            json = mapper.mapToJson(soups);
        }

        resp.getWriter().write(json);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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

