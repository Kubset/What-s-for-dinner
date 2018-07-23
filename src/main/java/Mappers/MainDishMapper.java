package Mappers;

import Model.MainDish;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainDishMapper implements Mapper<MainDish> {

    public MainDish map(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("dish_id");
            int favourite = resultSet.getInt("favourite");
            String name = resultSet.getString("name");

            return new MainDish(id, name, favourite);
    }
}
