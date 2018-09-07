package Mappers;

import Model.MainDish;
import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MainDishMapper extends AbstractMapper<MainDish> {

    @Override
    public MainDish map(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("dish_id");
            int favourite = resultSet.getInt("favourite");
            String name = resultSet.getString("name");

            return new MainDish(id, name, favourite);
    }
}
