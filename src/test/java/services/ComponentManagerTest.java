package services;

import Criteria.DishesByName;
import Criteria.SoupsByName;
import DAO.ComponentDAO;
import DAO.ConnectionProvider;
import DAO.MainDishDAO;
import DAO.SoupDAO;
import Model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComponentManagerTest {

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
    public void test_getComponentsOfSpecifiedSoup() {
        List<Component> expectedComponents = prepareExampleComponents();

        Soup soup = new Soup("exampleSoup");
        soup.setComponents(expectedComponents);

        soupManager.create(soup);
        soup = soupDAO.get(new SoupsByName("exampleSoup")).get(0);

        List<Component> actualComponents = componentManager.getComponentsOfSoup(soup.getId());

        assertEquals(expectedComponents, actualComponents);

        soupManager.delete(soup);
        componentManager.deleteAll();
    }

    @Test
    public void test_getComponentsOfNotPresentSoup() {
        List<Component> expectedComponents = new ArrayList<>();
        List<Component> actualComponents = componentManager.getComponentsOfSoup(0);

        assertEquals(expectedComponents, actualComponents);
    }

    @Test
    public void test_getComponentsOfSpecifiedMainDish() {
        List<Component> expectedComponents = prepareExampleComponents();

        MainDish mainDish = new MainDish("exampleSoup");
        mainDish.setComponents(expectedComponents);

        dishManager.create(mainDish);
        mainDish = mainDishDAO.get(new DishesByName("exampleSoup")).get(0);

        List<Component> actualComponents = componentManager.getComponentsOfDish(mainDish.getId());

        assertEquals(expectedComponents, actualComponents);

        dishManager.delete(mainDish);
        componentManager.deleteAll();
    }

    @Test
    public void test_getComponentsOfNotPresentMainDish() {
        List<Component> expectedComponents = new ArrayList<>();
        List<Component> actualComponents = componentManager.getComponentsOfDish(0);

        assertEquals(expectedComponents, actualComponents);
    }

    @Test
    public void test_deleteComponentsOfSpecifiedSoup() {
        List<Component> components = prepareExampleComponents();
        Soup soup = new Soup("exampleSoup");
        soup.setComponents(components);

        soupManager.create(soup);
        soup = soupDAO.get(new SoupsByName("exampleSoup")).get(0);
        componentManager.deleteComponentsOfSoup(soup);

        List<Component> actualComponents = soupManager.get(soup.getId()).getComponents();

        assertEquals(0, actualComponents.size());

        soupManager.delete(soup);
        componentManager.deleteAll();
    }

    @Test
    public void test_deleteComponentsOfSpecifiedDish() {
        List<Component> components = prepareExampleComponents();
        MainDish mainDish = new MainDish("exampleDish");
        mainDish.setComponents(components);

        dishManager.create(mainDish);
        mainDish = mainDishDAO.get(new DishesByName("exampleDish")).get(0);

        componentManager.deleteComponentsOfDish(mainDish);

        List<Component> actualComponents = dishManager.get(mainDish.getId()).getComponents();

        assertEquals(0, actualComponents.size());

        dishManager.delete(mainDish);
        componentManager.deleteAll();


    }

    @Test
    public void test_addComponentsToSpecifiedSoup() {
        List<Component> expectedComponents = prepareExampleComponents();
        Soup soup = new Soup("exampleSoup");
        soupDAO.add(soup);
        soup = soupDAO.get(new SoupsByName("exampleSoup")).get(0);
        soup.setComponents(expectedComponents);

        componentManager.addComponentsToSoup(soup);

        List<Component> actualComponents = soupManager.get(soup.getId()).getComponents();

        assertEquals(expectedComponents, actualComponents);

        soupManager.delete(soup);
        componentManager.deleteAll();


    }

    @Test
    public void test_addComponentsToSpecifiedDish() {
        List<Component> expectedComponents = prepareExampleComponents();
        MainDish mainDish = new MainDish("exampleDish");
        mainDishDAO.add(mainDish);
        mainDish = mainDishDAO.get(new DishesByName("exampleDish")).get(0);
        mainDish.setComponents(expectedComponents);

        componentManager.addComponentsToDish(mainDish);

        List<Component> actualComponents = dishManager.get(mainDish.getId()).getComponents();

        assertEquals(expectedComponents, actualComponents);

        dishManager.delete(mainDish);
        componentManager.deleteAll();

    }

    private List<Component> prepareExampleComponents() {
        List<Component> components = new ArrayList<>();
        for(int i=0 ;i<5; i++) {
            components.add(new Component("exampleComponent"+i, i, new Unit("unit")));
        }

        return components;
    }


}