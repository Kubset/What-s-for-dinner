package DAO;

import Criteria.AllComponents;
import Criteria.ComponentsByName;
import Criteria.SqlCriteria;
import Model.Component;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComponentDAOTest {
    private static ComponentDAO dao = new ComponentDAO();
    private static SqlCriteria allComponents = new AllComponents();
    private static List<Component> componentsBefore;
    private static List<Component> componentsAfter;
    private static List<Component> exampleComponents = new ArrayList<>();

    @BeforeEach
    public void initializeList() {
//       componentsBefore = dao.get(allComponents);
    }

    @AfterEach
    public void makeRollback() {


//        exampleComponents = null;
//        componentsAfter = null;
//        componentsBefore = null;
    }

    @DisplayName("Add multiple elements to database")
    @Test
    public void addToDatabaseTest() {
        componentsBefore = dao.get(allComponents);
        exampleComponents = prepareExampleComponents("example1", "example2", "example3");
        addExampleComponentsToDatabase();
        componentsAfter = dao.get(allComponents);

        componentsAfter.removeAll(componentsBefore);

        assertEquals(exampleComponents, componentsAfter);

        deleteExampleComponentsFromDatabase();
        exampleComponents.clear();
    }

    @DisplayName("Delete multiple elements from database")
    @Test
    public void removeFromDatabaseTest() {

        exampleComponents = prepareExampleComponents("example1toRemove", "example2ToRemove");
        addExampleComponentsToDatabase();

        componentsBefore = dao.get(allComponents);
        deleteExampleComponentsFromDatabase();
        componentsAfter = dao.get(allComponents);

        componentsBefore.removeAll(componentsAfter);

        assertEquals(exampleComponents, componentsBefore);

        exampleComponents.clear();
    }

    @DisplayName("Set multiple records in database")
    @Test
    public void setNameRecordInDatabase() {
        componentsBefore = dao.get(allComponents);
        exampleComponents = prepareExampleComponents("example1", "example2", "example3");
        addExampleComponentsToDatabase();
        setComponentDatabase("example1", "ex1");
        setComponentDatabase("example2", "ex2");
        setComponentDatabase("example3", "ex3");
        componentsAfter = dao.get(allComponents);

        componentsAfter.removeAll(componentsBefore);
        exampleComponents = prepareExampleComponents("ex1","ex2","ex3");

        deleteExampleComponentsFromDatabase();

        assertEquals(exampleComponents, componentsAfter);

        exampleComponents.clear();


    }

    private List<Component> prepareExampleComponents(String ... names) {
        List<Component> components = new ArrayList<>();
        for(String name : names) {
            components.add(new Component(name));
        }
        return components;
    }

    private void addExampleComponentsToDatabase() {
        for(Component c : exampleComponents) {
            dao.add(c);
        }
    }

    private void deleteExampleComponentsFromDatabase() {
        for(Component c : exampleComponents) {
            Component component = dao.get(new ComponentsByName(c.getName())).get(0);
            dao.delete(component);
        }
    }

    private void setComponentDatabase(String oldName, String newName) {
        Component c = dao.get(new ComponentsByName(oldName)).get(0);
        c.setName(newName);
        dao.update(c);
    }

}