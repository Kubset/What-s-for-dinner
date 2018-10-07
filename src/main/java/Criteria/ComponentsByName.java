package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComponentsByName extends GetByName {

    private final static String QUERY = "SELECT * FROM components WHERE name=?;";

    public ComponentsByName(String name) {
        super(QUERY, name);
    }
}
