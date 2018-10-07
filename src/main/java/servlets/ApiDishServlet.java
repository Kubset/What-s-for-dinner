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
import com.google.gson.Gson;
import services.DishManager;
import sun.applet.Main;

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
        Gson gson = new Gson();
        String json = req.getReader().readLine();
        DishManager dishManager = new DishManager();

        MainDish mainDish = gson.fromJson(json, MainDish.class);
        dishManager.create(mainDish);

        resp.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String json = req.getReader().readLine();
        DishManager dishManager = new DishManager();

        MainDish mainDish = gson.fromJson(json, MainDish.class);
        dishManager.edit(mainDish);

        resp.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] URL = req.getRequestURI().toString().split("/");
        DishManager dishManager = new DishManager();

        if(URL.length == 4 && URL[3].matches("\\d+")) {
            int id = Integer.valueOf(URL[3]);
            dishManager.delete(new MainDish(id));
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

