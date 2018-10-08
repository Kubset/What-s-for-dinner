package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AllUnits extends GetAll {

    private final static String QUERY = "SELECT * FROM units;";

    public AllUnits() {
        super(QUERY);
    }
}
