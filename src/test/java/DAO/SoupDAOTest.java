package DAO;

import Criteria.AllSoups;
import Criteria.SoupsByName;
import Criteria.SqlCriteria;
import Model.Soup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SoupDAOTest {


    private static SoupDAO dao = new SoupDAO();
    private static SqlCriteria allSoups = new AllSoups();
    private static List<Soup> soupsBefore;
    private static List<Soup> soupsAfter;
    private static List<Soup> exampleSoups = new ArrayList<>();

    @BeforeAll
    public static void setup() {
        ConnectionProvider.setPropertiesPath("src/test/resources");
    }

    @DisplayName("Add multiple elements to database")
    @Test
    public void addToDatabaseTest() {
        soupsBefore = dao.get(allSoups);

        String[] names = {"example1", "example2", "example3"};
        int[] favourites = {1,2,3};
        exampleSoups = prepareExampleSoups(names);
        addExampleSoupsToDatabase();
        soupsAfter = dao.get(allSoups);

        soupsAfter.removeAll(soupsBefore);

        assertEquals(exampleSoups, soupsAfter);

        deleteExampleSoupsFromDatabase();
        exampleSoups.clear();
    }

    @DisplayName("Delete multiple elements from database")
    @Test
    public void removeFromDatabaseTest() {

        String[] names = {"example1", "example2", "example3"};
        int[] favourites = {4,7,4};
        exampleSoups = prepareExampleSoups(names);
        addExampleSoupsToDatabase();

        soupsBefore = dao.get(allSoups);
        deleteExampleSoupsFromDatabase();

        soupsAfter = dao.get(allSoups);

        soupsBefore.removeAll(soupsAfter);

        assertEquals(exampleSoups, soupsBefore);

        exampleSoups.clear();
    }

    @DisplayName("Set multiple records in database")
    @Test
    public void setNameRecordInDatabase() {
        soupsBefore = dao.get(allSoups);
        String[] oldNames = {"example1", "example2", "example3"};
        exampleSoups = prepareExampleSoups(oldNames);
        addExampleSoupsToDatabase();
        setSoupDatabase("example1", "ex1");
        setSoupDatabase("example2", "ex2");
        setSoupDatabase("example3", "ex3");
        setSoupDatabase("ex1", 1);
        setSoupDatabase("ex2", 2);
        setSoupDatabase("ex3", 3);

        soupsAfter = dao.get(allSoups);

        String[] newNames = {"ex1", "ex2", "ex3"};
        int[] newFavourites = {1,2,3};

        exampleSoups = prepareExampleSoups(newNames,newFavourites);

        soupsAfter.removeAll(soupsBefore);


        deleteExampleSoupsFromDatabase();

        assertEquals(exampleSoups, soupsAfter);

        exampleSoups.clear();
    }

    private List<Soup> prepareExampleSoups(String[] names) {
        List<Soup> Soups = new ArrayList<>();
        for(int i=0; i<names.length; i++) {
            Soups.add(new Soup(names[i]));
        }
        return Soups;
    }

    private List<Soup> prepareExampleSoups(String[] names, int[] favourites) {
        List<Soup> Soups = new ArrayList<>();
        Soup soup;
        for(int i=0; i<names.length; i++) {
            soup = new Soup(names[i]);
            soup.setFavourite(favourites[i]);

            Soups.add(soup);
        }
        return Soups;
    }

    private void addExampleSoupsToDatabase() {
        for(Soup c : exampleSoups) {
            dao.add(c);
        }
    }

    private void deleteExampleSoupsFromDatabase() {
        for(Soup c : exampleSoups) {
            Soup Soup = dao.get(new SoupsByName(c.getName())).get(0);
            dao.delete(Soup);
        }
    }

    private void setSoupDatabase(String oldName, String newName) {
        Soup c = dao.get(new SoupsByName(oldName)).get(0);
        c.setName(newName);
        dao.update(c);
    }

    private void setSoupDatabase(String name, int newFavourite) {
        Soup c = dao.get(new SoupsByName(name)).get(0);
        c.setFavourite(newFavourite);
        dao.update(c);
    }



}