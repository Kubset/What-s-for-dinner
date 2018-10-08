package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComponentById extends GetById {

    private static final String QUERY = "SELECT * FROM components WHERE component_id=?;";

    public ComponentById(int id) {
        super(QUERY, id);
    }
}
