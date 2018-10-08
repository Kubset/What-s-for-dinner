package DAO;

import Criteria.AllDishes;
import Criteria.DishesByName;
import Criteria.SqlCriteria;
import Model.MainDish;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainDishDAOTest {

    private static MainDishDAO dao = new MainDishDAO();
    private static SqlCriteria allDishes = new AllDishes();
    private static List<MainDish> dishesBefore;
    private static List<MainDish> dishesAfter;
    private static List<MainDish> exampleDishes = new ArrayList<>();

    @BeforeAll
    public static void setup() {
        ConnectionProvider.setPropertiesPath("src/test/resources");
    }

    @DisplayName("Add multiple elements to database")
    @Test
    public void addToDatabaseTest() {
        dishesBefore = dao.get(allDishes);

        String[] names = {"example1", "example2", "example3"};
        exampleDishes = prepareExampleDishes(names);
        addExampleDishesToDatabase();
        dishesAfter = dao.get(allDishes);

        dishesAfter.removeAll(dishesBefore);

        assertEquals(exampleDishes, dishesAfter);

        deleteExampleDishesFromDatabase();
        exampleDishes.clear();
    }

    @DisplayName("Delete multiple elements from database")
    @Test
    public void removeFromDatabaseTest() {

        String[] names = {"example1", "example2", "example3"};
        exampleDishes = prepareExampleDishes(names);
        addExampleDishesToDatabase();

        dishesBefore = dao.get(allDishes);
        deleteExampleDishesFromDatabase();
        dishesAfter = dao.get(allDishes);

        dishesBefore.removeAll(dishesAfter);

        assertEquals(exampleDishes, dishesBefore);

        exampleDishes.clear();
    }

    @DisplayName("Set multiple records in database")
    @Test
    public void setNameRecordInDatabase() {
        dishesBefore = dao.get(allDishes);
        String[] oldNames = {"example1", "example2", "example3"};
        exampleDishes = prepareExampleDishes(oldNames);
        addExampleDishesToDatabase();
        setDishDatabase("example1", "ex1");
        setDishDatabase("example2", "ex2");
        setDishDatabase("example3", "ex3");

        dishesAfter = dao.get(allDishes);

        String[] newNames = {"ex1", "ex2", "ex3"};
        dishesAfter.removeAll(dishesBefore);
        exampleDishes = prepareExampleDishes(newNames);

        deleteExampleDishesFromDatabase();

        assertEquals(exampleDishes, dishesAfter);

        exampleDishes.clear();
    }

    private List<MainDish> prepareExampleDishes(String[] names) {
        List<MainDish> dishes = new ArrayList<>();
        for(int i=0; i<names.length; i++) {
            dishes.add(new MainDish(names[i]));
        }
        return dishes;
    }

    private void addExampleDishesToDatabase() {
        for(MainDish c : exampleDishes) {
            dao.add(c);
        }
    }

    private void deleteExampleDishesFromDatabase() {
        for(MainDish c : exampleDishes) {
            MainDish mainDish = dao.get(new DishesByName(c.getName())).get(0);
            dao.delete(mainDish);
        }
    }

    private void setDishDatabase(String oldName, String newName) {
        MainDish c = dao.get(new DishesByName(oldName)).get(0);
        c.setName(newName);
        dao.update(c);
    }



}