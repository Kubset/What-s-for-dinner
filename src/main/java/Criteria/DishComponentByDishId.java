package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DishComponentByDishId extends GetById{
    private static final String QUERY = "SELECT * FROM dish_components WHERE dish_id=?;";

    public DishComponentByDishId(int id) {
        super(QUERY, id);
    }
}
