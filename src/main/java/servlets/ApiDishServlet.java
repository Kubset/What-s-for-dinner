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
        String json;

        if(URL.length == 4 && URL[3].matches("\\d+")) {
            MainDish mainDish = dishManager.get(Integer.parseInt(URL[3]));
            json = mapper.mapToJson(mainDish);
        } else {
            List<MainDish> mainDish = dishManager.getAll();
            json = mapper.mapToJson(mainDish);
        }

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
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

