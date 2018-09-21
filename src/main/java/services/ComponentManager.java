package services;

import Criteria.*;
import DAO.*;
import Mappers.MainDishMapper;
import Mappers.Mapper;
import Model.Component;
import Model.DishComponent;
import Model.MainDish;
import Model.SoupComponent;

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

}
