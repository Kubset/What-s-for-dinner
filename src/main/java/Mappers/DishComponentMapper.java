package Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DishComponentMapper implements Mapper<DishComponent> {

    public DishComponent map(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("dish_component_id");
            int componentId = resultSet.getInt("component_id");
            int DishId = resultSet.getInt("dish_id");

            return new DishComponent(id, componentId, DishId);
    }
}
