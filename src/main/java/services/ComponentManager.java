package services;

import Criteria.*;
import DAO.*;
import Mappers.ComponentMapper;
import Mappers.MainDishMapper;
import Mappers.Mapper;
import Model.*;

import java.util.ArrayList;
import java.util.List;

public class ComponentManager implements Service<Component>{

    private DishComponentDAO dishComponentDAO;
    private SoupComponentDAO soupComponentDAO;
    private ComponentDAO componentDAO;


    public ComponentManager() {
       this.dishComponentDAO = new DishComponentDAO();
       this.soupComponentDAO = new SoupComponentDAO();
       this.componentDAO = new ComponentDAO();
    }




     public List<Component> getComponentsOfSoup(int soupId) {
        List<SoupComponent> soupComponents = soupComponentDAO.get(new SoupComponentBySoupId(soupId));
        List<Component> components = new ArrayList<>();


        for (SoupComponent sc : soupComponents) {
            components.add(componentDAO.get(new ComponentById(sc.getComponentId())).get(0));
            components.get(components.size() - 1).setCount(sc.getCount());
            components.get(components.size() - 1).setUnit(sc.getUnit());
        }
        return components;
    }

    public List<Component> getComponentsOfDish(int dishId) {
        List<DishComponent> dishComponents = dishComponentDAO.get(new DishComponentByDishId(dishId));
        List<Component> components = new ArrayList<>();


        for (DishComponent dc : dishComponents) {
            components.add(componentDAO.get(new ComponentById(dc.getComponentId())).get(0));
            components.get(components.size() - 1).setCount(dc.getCount());
            components.get(components.size() - 1).setUnit(dc.getUnit());
        }

        return components;
    }

    public void deleteComponentsOfSoup(Soup soup) {
        List<SoupComponent> soupComponents = soupComponentDAO.get(new SoupComponentBySoupId(soup.getId()));

        for (SoupComponent sc : soupComponents) {
            soupComponentDAO.delete(sc);
        }
    }

    public void deleteComponentsOfDish(MainDish mainDish) {
        List<DishComponent> dishComponents = dishComponentDAO.get(new DishComponentByDishId(mainDish.getId()));

        for (DishComponent dc : dishComponents) {
            dishComponentDAO.delete(dc);
        }
    }

    public void addComponentsToSoup(Soup soup) {
        List<Component> components = soup.getComponents();

        SqlCriteria componentCriteria;
        for (Component component : components) {
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
        List<Component> components = mainDish.getComponents();

        SqlCriteria componentCriteria;
        for (Component component : components) {
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
}
