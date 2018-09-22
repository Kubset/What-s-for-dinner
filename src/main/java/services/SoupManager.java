package services;

import Criteria.*;
import DAO.ComponentDAO;
import DAO.SoupComponentDAO;
import DAO.SoupDAO;
import Mappers.Mapper;
import Mappers.SoupMapper;
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
        //TODO: only part implementation, shoud return completly object with components
        Mapper<Soup> mapper = new SoupMapper();
        SoupDAO soupDAO = new SoupDAO();
        SqlCriteria criteria = new AllSoups();

        return soupDAO.get(criteria);
    }

    public Soup get(int id) {
        SoupDAO soupDAO = new SoupDAO();
        ComponentDAO componentDAO = new ComponentDAO();
        SoupComponentDAO soupComponentDAO = new SoupComponentDAO();
        ComponentManager componentManager = new ComponentManager();
        Soup soup = null;

        try {
            soup = soupDAO.get(new SoupById(id)).get(0);

            List<SoupComponent> soupComponents = soupComponentDAO.get(new SoupComponentBySoupId(id));
            List<Component> components = componentManager.getComponentsOfSoup(soup.getId());

            soup.setComponents(components);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Soup with this ID does no exist");
        }
        return soup;
    }


}
