package services;

import Criteria.ComponentsByName;
import Criteria.SoupsByName;
import Criteria.SqlCriteria;
import DAO.ComponentDAO;
import DAO.SoupComponentDAO;
import DAO.SoupDAO;
import Model.Component;
import Model.Soup;
import Model.SoupComponent;

import java.util.ArrayList;
import java.util.List;

public class SoupManager {

    public void create(Soup soup) {
        SoupDAO soupDAO = new SoupDAO();
        ComponentDAO componentDAO = new ComponentDAO();
        SoupComponentDAO soupComponentDAO = new SoupComponentDAO();

        soupDAO.add(soup);

        SqlCriteria soupCriteria = new SoupsByName(soup.getName());
        SqlCriteria componentCriteria;

        int soupId = soupDAO.get(soupCriteria).get(0).getId();
        for (Component component : soup.getComponents()) {
            componentCriteria = new ComponentsByName(component.getName());
            if (componentDAO.get(componentCriteria).size() == 0) {
                componentDAO.add(component);
            }
            Component _component = componentDAO.get(componentCriteria).get(0);
            soupComponentDAO.add(new SoupComponent(soupId,
                                                   _component.getId(),
                                                   component.getCount(),
                                                   component.getUnit()));
        }
    }

    public List<Soup> getAll() {
        //TODO not implemented yet
        return null;
    }

    public Soup get(int id) {
        //TODO: not implemented yet
        return null;
    }
}
