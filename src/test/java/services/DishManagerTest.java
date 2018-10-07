package services;

import Criteria.AllComponents;
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
    public void test_createMainDishInDatabase() {
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
        componentManager.deleteAllComponents();




    }

    @Test
    public void test_createFewDishesInDatabaseWithCommonComponents() {
        List<Component> exampleComponents = prepareExampleComponents();
        MainDish firstDish = new MainDish("exampleDish1");
        firstDish.setComponents(exampleComponents);
        MainDish secondDish = new MainDish("exampleDish2");
        secondDish.setComponents(exampleComponents);

        dishManager.create(firstDish);
        dishManager.create(secondDish);
        firstDish.setId(mainDishDAO.get(new DishesByName("exampleDish1")).get(0).getId());
        secondDish.setId(mainDishDAO.get(new DishesByName("exampleDish2")).get(0).getId());

        MainDish actualMainDish1 = dishManager.get(firstDish.getId());
        MainDish actualMainDish2 = dishManager.get(secondDish.getId());
        List<Component> components = componentDAO.get(new AllComponents());

        assertAll(() -> {
            assertEquals(actualMainDish1.getComponents(), actualMainDish2.getComponents());
            assertEquals(components, actualMainDish1.getComponents());
            assertEquals(components, actualMainDish2.getComponents());
        });

        dishManager.delete(actualMainDish1);
        dishManager.delete(actualMainDish2);
        componentManager.deleteAllComponents();

    }

    @Test
    public void test_getAllDishesFromEmptyDatabase() {
        List<MainDish> expectedDishes = new ArrayList<>();
        List<MainDish> allDishes = dishManager.getAll();

        assertEquals(expectedDishes, allDishes);

    }

    @Test
    public void test_getAllDishesFromDatabase() {
        List<Component> exampleComponents = prepareExampleComponents();
        MainDish firstDish = new MainDish("exampleDish1");
        firstDish.setComponents(exampleComponents);
        MainDish secondDish = new MainDish("exampleDish2");
        secondDish.setComponents(exampleComponents);

        dishManager.create(firstDish);
        dishManager.create(secondDish);
        firstDish.setId(mainDishDAO.get(new DishesByName("exampleDish1")).get(0).getId());
        secondDish.setId(mainDishDAO.get(new DishesByName("exampleDish2")).get(0).getId());

        List<MainDish> actualDishes = dishManager.getAll();

        assertAll(() -> {
            assertEquals(actualDishes.get(0), firstDish);
            assertEquals(actualDishes.get(1), secondDish);
        });

        dishManager.delete(firstDish);
        dishManager.delete(secondDish);
        componentManager.deleteAllComponents();

    }

    @Test
    public void test_editDishinDatabase() {

        List<Component> exampleComponents = prepareExampleComponents();
        List<Component> newComponents = new ArrayList<>();
        newComponents.add(new Component("oneComponent",100,new Unit("unit")));

        MainDish mainDish = new MainDish("exampleDish1");
        mainDish.setComponents(exampleComponents);

        dishManager.create(mainDish);
        mainDish.setId(mainDishDAO.get(new DishesByName("exampleDish1")).get(0).getId());

        mainDish.setComponents(newComponents);

        dishManager.edit(mainDish);

        assertEquals(newComponents, dishManager.get(mainDish.getId()).getComponents());

        dishManager.delete(mainDish);
        componentManager.deleteAllComponents();
    }

    @Test
    public void test_deleteDishFromDatabase() {
        List<Component> exampleComponents = prepareExampleComponents();

        MainDish mainDish = new MainDish("exampleDish1");
        mainDish.setComponents(exampleComponents);

        dishManager.create(mainDish);
        mainDish.setId(mainDishDAO.get(new DishesByName("exampleDish1")).get(0).getId());

        dishManager.delete(mainDish);

        List<MainDish> actualDishes = dishManager.getAll();

        assertEquals(0, actualDishes.size());

        componentManager.deleteAllComponents();

    }

    private List<Component> prepareExampleComponents() {
        List<Component> components = new ArrayList<>();
        for(int i=0 ;i<5; i++) {
            components.add(new Component("exampleComponent"+i, i, new Unit("unit")));
        }

        return components;
    }

}