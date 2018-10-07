package services;

import Criteria.*;
import DAO.*;
import Mappers.MainDishMapper;
import Mappers.Mapper;
import Model.*;

import java.util.ArrayList;
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

    public List<MainDish> getAll() {
        MainDishDAO mainDishDAO = new MainDishDAO();
        Mapper<MainDish> mapper = new MainDishMapper();
        ComponentManager componentManager = new ComponentManager();

        List<MainDish> mainDishes = mainDishDAO.get(new AllDishes());

        for(MainDish mainDish : mainDishes) {
            mainDish.setComponents(componentManager.getComponentsOfDish(mainDish.getId()));
        }

        return mainDishes;
    }

    public MainDish get(int id) {
        MainDishDAO mainDishDAO = new MainDishDAO();
        ComponentDAO componentDAO = new ComponentDAO();
        DishComponentDAO dishComponentDAO = new DishComponentDAO();
        ComponentManager componentManager = new ComponentManager();

        MainDish mainDish = null;

        try {
            mainDish = mainDishDAO.get(new DishById(id)).get(0);

            List<DishComponent> dishComponents = dishComponentDAO.get(new DishComponentByDishId(id));
            List<Component> components = componentManager.getComponentsOfDish(mainDish.getId());

            mainDish.setComponents(components);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Soup with this ID does no exist");
        }
        return mainDish;
    }

    public void edit(MainDish mainDish) {
       MainDishDAO mainDishDAO = new MainDishDAO();
       DishComponentDAO dishComponentDAO = new DishComponentDAO();
       ComponentManager componentManager = new ComponentManager();

       mainDishDAO.update(mainDish);
       componentManager.deleteComponentsOfDish(mainDish);
       componentManager.addComponentsToDish(mainDish);
    }

    public void delete(MainDish mainDish) {
       MainDishDAO mainDishDAO = new MainDishDAO();
       DishComponentDAO dishComponentDAO = new DishComponentDAO();
       SqlCriteria criteria = new DishComponentByDishId(mainDish.getId());

       List<DishComponent> dishComponents = dishComponentDAO.get(criteria);

       for(DishComponent dc : dishComponents) {
           dishComponentDAO.delete(dc);
       }

       mainDishDAO.delete(mainDish);
    }
}
