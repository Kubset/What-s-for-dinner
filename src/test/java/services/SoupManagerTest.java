package services;

import Criteria.AllComponents;
import Criteria.SoupsByName;
import DAO.ComponentDAO;
import DAO.ConnectionProvider;
import DAO.MainDishDAO;
import DAO.SoupDAO;
import Model.Component;
import Model.Soup;
import Model.Unit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SoupManagerTest {

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
    public void test_createSoupInDatabase() {
        List<Component> exampleComponents = prepareExampleComponents();
        Soup expectedSoup = new Soup("exampleSoup");
        expectedSoup.setComponents(exampleComponents);

        soupManager.create(expectedSoup);
        expectedSoup.setId(soupDAO.get(new SoupsByName("exampleSoup")).get(0).getId());

        Soup actualSoup = soupManager.get(expectedSoup.getId());

        assertAll(() -> {
            assertEquals(expectedSoup.getName(), actualSoup.getName());
            assertEquals(expectedSoup.getComponents(), actualSoup.getComponents());
            assertEquals(expectedSoup.getRecipe(), actualSoup.getRecipe());
        });

        soupManager.delete(expectedSoup);
        componentManager.deleteAllComponents();
    }
