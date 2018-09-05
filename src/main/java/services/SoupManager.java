package services;

import Criteria.SoupsByName;
import Criteria.SqlCriteria;
import DAO.ComponentDAO;
import DAO.SoupComponentDAO;
import DAO.SoupDAO;
import Model.Component;
import Model.Soup;
import Model.SoupComponent;

import java.util.ArrayList;
import java.util.List;

public class SoupManager {

    public void create(Soup soup) {
        SoupDAO soupDAO = new SoupDAO();
        ComponentDAO componentDAO = new ComponentDAO();
        SoupComponentDAO soupComponentDAO = new SoupComponentDAO();

        List<SoupComponent> soupComponents = new ArrayList<>();

        soupDAO.add(soup);

        SqlCriteria criteria = new SoupsByName(soup.getName());
        int soupId = soupDAO.get(criteria).get(0).getId();

        for(Component component: soup.getComponents()) {
            soupComponents.add(new SoupComponent(component.getId(), soupId));
            componentDAO.add(component);
        }

    }

    public List<Soup> getAll() {

    }

    public Soup get(int id) {

    }
}
