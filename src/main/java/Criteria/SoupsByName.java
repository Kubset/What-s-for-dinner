package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SoupsByName extends GetByName {

    private final static String QUERY = "SELECT * FROM soup WHERE name=?;";

    public SoupsByName(String name) {
        super(QUERY, name);
    }
}
