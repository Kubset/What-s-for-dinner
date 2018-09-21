package servlets;

import Criteria.AllComponents;
import Criteria.AllSoups;
import Criteria.SqlCriteria;
import DAO.ComponentDAO;
import DAO.SoupDAO;
import Mappers.ComponentMapper;
import Mappers.Mapper;
import Mappers.SoupMapper;
import Model.Component;
import Model.Soup;
import services.SoupManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ApiSoupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] URL = req.getRequestURI().toString().split("/");
        Mapper<Soup> mapper = new SoupMapper();
        SoupManager soupManager = new SoupManager();
        String json;

        if(URL.length == 4 && URL[3].matches("\\d+")) {
                Soup soup = soupManager.get(Integer.parseInt(URL[3]));
                json = mapper.mapToJson(soup);
        } else {
            List<Soup> soups = soupManager.getAll();
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

