package Mappers;

import Model.DishComponent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DishComponentMapper implements Mapper<DishComponent> {

    @Override
    public DishComponent map(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("dish_component_id");
            int componentId = resultSet.getInt("component_id");
            int DishId = resultSet.getInt("dish_id");

            return new DishComponent(id, componentId, DishId);
    }

    @Override
    public String mapToJson(List<DishComponent> entities) {
        //TODO: not implemented yet
        return null;
    }

    @Override
    public String mapToJson(DishComponent entity) {
        //TODO: not implemented yet
        return null;
    }
}
