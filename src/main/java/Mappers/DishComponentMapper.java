package Mappers;

import Model.DishComponent;
import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DishComponentMapper extends AbstractMapper<DishComponent> {

    @Override
    public DishComponent map(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("dish_component_id");
            int componentId = resultSet.getInt("component_id");
            int DishId = resultSet.getInt("dish_id");

            return new DishComponent(id, componentId, DishId);
    }
}
