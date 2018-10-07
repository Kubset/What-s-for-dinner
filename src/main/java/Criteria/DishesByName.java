package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DishesByName extends GetByName {

    private final static String QUERY = "SELECT * FROM main_dish WHERE name=?;";

    public DishesByName(String name) {
        super(QUERY, name);
    }
}
