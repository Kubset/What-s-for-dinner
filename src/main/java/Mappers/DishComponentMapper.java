package Mappers;

import Model.DishComponent;
import Model.Unit;
import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DishComponentMapper extends AbstractMapper<DishComponent> {

    @Override
    public DishComponent map(ResultSet resultSet) throws SQLException {
            int componentId = resultSet.getInt("component_id");
            int DishId = resultSet.getInt("dish_id");
            int id = resultSet.getInt("dish_component_id");
            int count = resultSet.getInt("count");
            String unit = resultSet.getString("unit");

            return new DishComponent(id, DishId, componentId, count, new Unit(unit));
    }
}
