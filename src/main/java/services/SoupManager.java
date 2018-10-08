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

public class SoupManager implements Service<Soup>{

    private SoupDAO soupDAO;
    private SoupComponentDAO soupComponentDAO;
    private ComponentDAO componentDAO;

    public SoupManager() {
        this.soupDAO = new SoupDAO();
        this.soupComponentDAO = new SoupComponentDAO();
        this.componentDAO = new ComponentDAO();
    }

    public void create(Soup soup) {
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
        Mapper<Soup> mapper = new SoupMapper();
        ComponentManager componentManager = new ComponentManager();

        List<Soup> soups = soupDAO.get(new AllSoups());

        for(Soup soup : soups) {
            soup.setComponents(componentManager.getComponentsOfSoup(soup.getId()));
        }

        return soups;
    }

    public Soup get(int id) {
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

    public void edit(Soup soup) {
       ComponentManager componentManager = new ComponentManager();

       soupDAO.update(soup);
       componentManager.deleteComponentsOfSoup(soup);
       componentManager.addComponentsToSoup(soup);
    }

    public void delete(Soup soup) {
       SqlCriteria criteria = new SoupComponentBySoupId(soup.getId());

       List<SoupComponent> soupComponents = soupComponentDAO.get(criteria);

       for(SoupComponent sc : soupComponents) {
           soupComponentDAO.delete(sc);
       }

       soupDAO.delete(soup);
    }

    public void deleteAll() {
        SoupComponentDAO soupComponentDAO = new SoupComponentDAO();

        List<Soup> soups = soupDAO.get(new AllSoups());
        List<SoupComponent> dishComponents = soupComponentDAO.get(new AllSoupComponents());

        for(Soup s : soups) {
            soupDAO.delete(s);
        }

        for(SoupComponent sc : dishComponents) {
            soupComponentDAO.delete(sc);
        }
    }
}
