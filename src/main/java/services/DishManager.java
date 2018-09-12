package services;

import Criteria.ComponentsByName;
import Criteria.DishesByName;
import Criteria.SoupsByName;
import Criteria.SqlCriteria;
import DAO.*;
import Model.*;

import java.util.List;

public class DishManager {

    public void create(MainDish mainDish) {
        MainDishDAO mainDishDAO = new MainDishDAO();
        ComponentDAO componentDAO = new ComponentDAO();
        DishComponentDAO dishComponentDAO = new DishComponentDAO();

        mainDishDAO.add(mainDish);

        SqlCriteria dishCriteria = new DishesByName(mainDish.getName());
        SqlCriteria componentCriteria;

        int dishId = mainDishDAO.get(dishCriteria).get(0).getId();
        for (Component component : mainDish.getComponents()) {
            componentCriteria = new ComponentsByName(component.getName());
            if (componentDAO.get(componentCriteria).size() == 0) {
                componentDAO.add(component);
            }
            Component _component = componentDAO.get(componentCriteria).get(0);
            dishComponentDAO.add(new DishComponent(dishId,
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
