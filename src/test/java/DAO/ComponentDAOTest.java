package DAO;

import Criteria.AllComponents;
import Criteria.ComponentsByName;
import Criteria.SqlCriteria;
import Model.Component;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

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
       componentsBefore = dao.get(allComponents);
    }

    @AfterEach
    public void makeRollback() {
        for(Component c : exampleComponents) {
            Component component = dao.get(new ComponentsByName(c.getName())).get(0);
            dao.delete(component);
        }
        exampleComponents = null;
        componentsAfter = null;
        componentsBefore = null;
    }

    @Test
    public void addToDatabaseTest() {
        exampleComponents.add(new Component("example1"));

        dao.add(exampleComponents.get(0));
        componentsAfter = dao.get(allComponents);
        componentsAfter.removeAll(componentsBefore);

        assertEquals(exampleComponents, componentsAfter);
    }

}