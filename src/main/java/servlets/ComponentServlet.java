package servlets;

import Criteria.AllComponents;
import Criteria.SqlCriteria;
import DAO.ComponentDAO;
import Mappers.ComponentMapper;
import Mappers.Mapper;
import Model.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ComponentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] URL = req.getRequestURI().toString().split("/");

        if(URL.length >= 4) {
            //get specified id of component and return as json
            resp.getWriter().write(URL[3]);
        } else {
            ComponentDAO componentDAO = new ComponentDAO();
            SqlCriteria criteria = new AllComponents();
            Mapper<Component> mapper = new ComponentMapper();

            List<Component> components = componentDAO.get(criteria);
            String json = mapper.mapToJson(components);

            resp.getWriter().write(json);
        }

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

