package services;

import Criteria.*;
import DAO.*;
import Mappers.ComponentMapper;
import Mappers.MainDishMapper;
import Mappers.Mapper;
import Model.*;

import java.util.ArrayList;
import java.util.List;

public class ComponentManager {

    public List<Component> getComponentsOfSoup(int soupId) {
        ComponentDAO componentDAO = new ComponentDAO();
        SoupComponentDAO soupComponentDAO = new SoupComponentDAO();

        List<SoupComponent> soupComponents = soupComponentDAO.get(new SoupComponentBySoupId(soupId));
        List<Component> components = new ArrayList<>();


        for(SoupComponent sc : soupComponents) {
            components.add(componentDAO.get(new ComponentById(sc.getComponentId())).get(0));
            components.get(components.size()-1).setCount(sc.getCount());
            components.get(components.size()-1).setUnit(sc.getUnit());
        }
        return components;
    }

    public List<Component> getComponentsOfDish(int dishId) {
        ComponentDAO componentDAO = new ComponentDAO();
        DishComponentDAO dishComponentDAO = new DishComponentDAO();

        List<DishComponent> dishComponents = dishComponentDAO.get(new DishComponentByDishId(dishId));
        List<Component> components = new ArrayList<>();


        for(DishComponent dc : dishComponents) {
            components.add(componentDAO.get(new ComponentById(dc.getComponentId())).get(0));
            components.get(components.size()-1).setCount(dc.getCount());
            components.get(components.size()-1).setUnit(dc.getUnit());
        }

        return components;
    }

    public void deleteComponentsOfSoup(Soup soup) {
        ComponentDAO componentDAO = new ComponentDAO();
        SoupComponentDAO soupComponentDAO = new SoupComponentDAO();

        List<SoupComponent> soupComponents = soupComponentDAO.get(new SoupComponentBySoupId(soup.getId()));

        for(SoupComponent sc : soupComponents) {
            soupComponentDAO.delete(sc);
        }
    }

    public void deleteComponentsOfDish(MainDish mainDish) {
        ComponentDAO componentDAO = new ComponentDAO();
        DishComponentDAO dishComponentDAO = new DishComponentDAO();

        List<DishComponent> dishComponents = dishComponentDAO.get(new DishComponentByDishId(mainDish.getId()));

        for(DishComponent dc : dishComponents) {
            dishComponentDAO.delete(dc);
        }
    }

    public void addComponentsToSoup(Soup soup) {
        SoupComponentDAO soupComponentDAO = new SoupComponentDAO();
        ComponentDAO componentDAO = new ComponentDAO();
        List<Component> components = soup.getComponents();

        SqlCriteria componentCriteria;
        for(Component component : components) {
           componentCriteria = new ComponentsByName(component.getName());
            if (componentDAO.get(componentCriteria).size() == 0) {
                componentDAO.add(component);
            }
            Component _component = componentDAO.get(componentCriteria).get(0);
            soupComponentDAO.add(new SoupComponent(soup.getId(),
                                                   _component.getId(),
                                                   component.getCount(),
                                                   component.getUnit()));
        }


    }

    public void addComponentsToDish(MainDish mainDish) {
        DishComponentDAO dishComponentDAO = new DishComponentDAO();
        ComponentDAO componentDAO = new ComponentDAO();
        List<Component> components = mainDish.getComponents();

        SqlCriteria componentCriteria;
        for(Component component : components) {
           componentCriteria = new ComponentsByName(component.getName());
            if (componentDAO.get(componentCriteria).size() == 0) {
                componentDAO.add(component);
            }
            Component _component = componentDAO.get(componentCriteria).get(0);
            dishComponentDAO.add(new DishComponent(mainDish.getId(),
                                                   _component.getId(),
                                                   component.getCount(),
                                                   component.getUnit()));
        }

    }

    public void deleteAllComponents() {
        ComponentDAO componentDAO = new ComponentDAO();
        Mapper<Component> mapper = new ComponentMapper();

        List<Component> components = componentDAO.get(new AllComponents());

        for(Component component : components) {
            componentDAO.delete(component);
        }
    }
}
