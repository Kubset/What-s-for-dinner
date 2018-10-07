package services;

import Criteria.DishesByName;
import DAO.ComponentDAO;
import DAO.ConnectionProvider;
import DAO.MainDishDAO;
import DAO.SoupDAO;
import Model.Component;
import Model.MainDish;
import Model.Unit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class DishManagerTest {

    private static ComponentDAO componentDAO = new ComponentDAO();
    private static SoupDAO soupDAO = new SoupDAO();
    private static MainDishDAO mainDishDAO = new MainDishDAO();
    private static SoupManager soupManager = new SoupManager();
    private static ComponentManager componentManager = new ComponentManager();
    private static DishManager dishManager = new DishManager();

    @BeforeAll
    public static void setup() {
        ConnectionProvider.setPropertiesPath("src/test/resources");
    }

    @Test
    public void test_createMainDishInDataBase() {
        List<Component> exampleComponents = prepareExampleComponents();
        MainDish expectedMainDish = new MainDish("exampleDish");
        expectedMainDish.setComponents(exampleComponents);

        dishManager.create(expectedMainDish);
        expectedMainDish.setId(mainDishDAO.get(new DishesByName("exampleDish")).get(0).getId());

        MainDish actualMainDish = dishManager.get(expectedMainDish.getId());

        assertAll(() -> {
            assertEquals(expectedMainDish.getName(), actualMainDish.getName());
            assertEquals(expectedMainDish.getComponents(), actualMainDish.getComponents());
            assertEquals(expectedMainDish.getRecipe(), actualMainDish.getRecipe());
        });

        dishManager.delete(expectedMainDish);




    }

    private List<Component> prepareExampleComponents() {
        List<Component> components = new ArrayList<>();
        for(int i=0 ;i<5; i++) {
            components.add(new Component("exampleComponent"+i, i, new Unit("unit")));
        }

        return components;
    }

}