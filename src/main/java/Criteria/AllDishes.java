package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AllDishes extends GetAll {

    private final static String QUERY = "SELECT * FROM main_dish;";

    public AllDishes() {
        super(QUERY);
    }
}
