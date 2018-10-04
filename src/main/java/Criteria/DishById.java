package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DishById extends GetById {

    private static final String QUERY = "SELECT * FROM main_dish WHERE dish_id=?;";

    public DishById(int id) {
        super(QUERY, id);
    }
}
