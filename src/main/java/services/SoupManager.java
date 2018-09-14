package services;

import Criteria.*;
import DAO.ComponentDAO;
import DAO.SoupComponentDAO;
import DAO.SoupDAO;
import Model.Component;
import Model.Soup;
import Model.SoupComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        SoupDAO soupDAO = new SoupDAO();
        ComponentDAO componentDAO = new ComponentDAO();
        SoupComponentDAO soupComponentDAO = new SoupComponentDAO();


        Soup soup = soupDAO.get(new SoupById(id)).get(0);

        List<SoupComponent> soupComponents = soupComponentDAO.get(new SoupComponentBySoupId(id));
        List<Component> components = new ArrayList<>();

        for(SoupComponent sc : soupComponents) {
            components.add(componentDAO.get(new ComponentById(sc.getComponentId())).get(0));
        }
        soup.setComponents(components);

        return soup;
    }
}
