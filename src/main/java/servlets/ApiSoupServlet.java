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
import com.google.gson.Gson;
import services.SoupManager;

import javax.servlet.RequestDispatcher;
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

        if(!json.equals("null")) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("text/html; charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] URL = req.getRequestURI().toString().split("/");
        Gson gson = new Gson();
        SoupManager soupManager = new SoupManager();


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] URL = req.getRequestURI().toString().split("/");

        Gson gson = new Gson();
        SoupManager soupManager = new SoupManager();



    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] URL = req.getRequestURI().toString().split("/");
        SoupManager soupManager = new SoupManager();

        if(URL.length == 4 && URL[3].matches("\\d+")) {
            int id = Integer.valueOf(URL[3]);
            soupManager.delete(new Soup(id));
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

}

