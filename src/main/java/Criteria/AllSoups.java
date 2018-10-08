package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AllSoups extends GetAll {

    private final static String QUERY = "SELECT * FROM soup;";

    public AllSoups() {
        super(QUERY);
    }
}
