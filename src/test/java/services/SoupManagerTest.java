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

    @Test
    public void test_createFewSoupsInDatabaseWithCommonComponents() {
        List<Component> exampleComponents = prepareExampleComponents();
        Soup firstSoup = new Soup("exampleSoup1");
        firstSoup.setComponents(exampleComponents);
        Soup secondSoup = new Soup("exampleSoup2");
        secondSoup.setComponents(exampleComponents);

        soupManager.create(firstSoup);
        soupManager.create(secondSoup);
        firstSoup.setId(soupDAO.get(new SoupsByName("exampleSoup1")).get(0).getId());
        secondSoup.setId(soupDAO.get(new SoupsByName("exampleSoup2")).get(0).getId());

        Soup actualSoup1 = soupManager.get(firstSoup.getId());
        Soup actualSoup2 = soupManager.get(secondSoup.getId());
        List<Component> components = componentDAO.get(new AllComponents());

        assertAll(() -> {
            assertEquals(actualSoup1.getComponents(), actualSoup2.getComponents());
            assertEquals(components, actualSoup1.getComponents());
            assertEquals(components, actualSoup2.getComponents());
        });

        soupManager.delete(actualSoup1);
        soupManager.delete(actualSoup2);
        componentManager.deleteAllComponents();
    }

    @Test
    public void test_getAllSoupsFromEmptyDatabase() {
        List<Soup> expectedSoups = new ArrayList<>();
        List<Soup> allSoups = soupManager.getAll();

        assertEquals(expectedSoups, allSoups);
    }

    @Test
    public void test_getAllSoupsFromDatabase() {
        List<Component> exampleComponents = prepareExampleComponents();
        Soup firstSoup = new Soup("exampleSoup1");
        firstSoup.setComponents(exampleComponents);
        Soup secondSoup = new Soup("exampleSoup2");
        secondSoup.setComponents(exampleComponents);

        soupManager.create(firstSoup);
        soupManager.create(secondSoup);
        firstSoup.setId(soupDAO.get(new SoupsByName("exampleSoup1")).get(0).getId());
        secondSoup.setId(soupDAO.get(new SoupsByName("exampleSoup2")).get(0).getId());

        List<Soup> actualSoups = soupManager.getAll();

        assertAll(() -> {
            assertEquals(actualSoups.get(0), firstSoup);
            assertEquals(actualSoups.get(1), secondSoup);
        });

        soupManager.delete(firstSoup);
        soupManager.delete(secondSoup);
        componentManager.deleteAllComponents();
    }

    @Test
    public void test_editSoupInDatabase() {

        List<Component> exampleComponents = prepareExampleComponents();
        List<Component> newComponents = new ArrayList<>();
        newComponents.add(new Component("oneComponent",100,new Unit("unit")));

        Soup soup = new Soup("exampleSoup1");
        soup.setComponents(exampleComponents);

        soupManager.create(soup);
        soup.setId(soupDAO.get(new SoupsByName("exampleSoup1")).get(0).getId());

        soup.setComponents(newComponents);

        soupManager.edit(soup);

        assertEquals(newComponents, soupManager.get(soup.getId()).getComponents());

        soupManager.delete(soup);
        componentManager.deleteAllComponents();
    }

    @Test
    public void test_deleteSoupFromDatabase() {
        List<Component> exampleComponents = prepareExampleComponents();

        Soup soup = new Soup("exampleSoup1");
        soup.setComponents(exampleComponents);

        soupManager.create(soup);
        soup.setId(soupDAO.get(new SoupsByName("exampleSoup1")).get(0).getId());

        soupManager.delete(soup);

        List<Soup> actualSoups = soupManager.getAll();

        assertEquals(0, actualSoups.size());

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