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
    }

    @Test
    public void test_getComponentsOfNotPresentSoup() {
        List<Component> expectedComponents = new ArrayList<>();
        List<Component> actualComponents = componentManager.getComponentsOfSoup(0);

        assertEquals(expectedComponents, actualComponents);
    }


    private List<Component> prepareExampleComponents() {
        List<Component> components = new ArrayList<>();
        for(int i=0 ;i<5; i++) {
            components.add(new Component("exampleComponent"+i, i, new Unit("unit")));
        }

        return components;
    }


}