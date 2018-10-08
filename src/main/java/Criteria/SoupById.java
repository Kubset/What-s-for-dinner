package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SoupById extends GetById {

    private static final String QUERY = "SELECT * FROM soup WHERE soup_id=?;";

    public SoupById(int id) {
        super(QUERY, id);
    }
}
