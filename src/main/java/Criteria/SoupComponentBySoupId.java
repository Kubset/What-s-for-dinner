package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SoupComponentBySoupId extends GetById{
    private static final String QUERY = "SELECT * FROM soup_components WHERE soup_id=?;";

    public SoupComponentBySoupId(int id) {
        super(QUERY, id);
    }
}
