package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AllComponents extends GetAll {

    private final static String QUERY = "SELECT * FROM components;";

    public AllComponents() {
        super(QUERY);
    }
}
