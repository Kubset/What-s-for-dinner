package Mappers;

import Model.MainDish;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MainDishMapper implements Mapper<MainDish> {

    @Override
    public MainDish map(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("dish_id");
            int favourite = resultSet.getInt("favourite");
            String name = resultSet.getString("name");

            return new MainDish(id, name, favourite);
    }

    @Override
    public String mapToJson(List<MainDish> entities) {
        //TODO: not implemented yet
        return null;
    }

    @Override
    public String mapToJson(MainDish entity) {
        //TODO: not implemented yet
        return null;
    }
}
