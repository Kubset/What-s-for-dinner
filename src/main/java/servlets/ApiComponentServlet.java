package servlets;

import Mappers.ComponentMapper;
import Mappers.Mapper;
import Model.Component;
import com.google.gson.Gson;
import services.ComponentManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ApiComponentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] URL = req.getRequestURI().toString().split("/");
        String json;
        ComponentManager componentManager = new ComponentManager();
        Mapper<Component> mapper = new ComponentMapper();

        if(URL.length == 4 && URL[3].matches("\\d+")) {
            Component component = componentManager.get(Integer.parseInt(URL[3]));
            json = mapper.mapToJson(component);
        } else {

            List<Component> components = componentManager.getAll();
            json = mapper.mapToJson(components);

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
        ComponentManager componentManager = new ComponentManager();
        Mapper<Component> mapper = new ComponentMapper();

        if(URL.length == 3) {
            String json = req.getReader().readLine();
            Component component = gson.fromJson(json, Component.class);
            componentManager.create(component);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}

