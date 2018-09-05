package Mappers;

import Model.SoupComponent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SoupComponentMapper implements Mapper<SoupComponent>{

    @Override
    public SoupComponent map(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("soup_component_id");
            int componentId = resultSet.getInt("component_id");
            int DishId = resultSet.getInt("soup_id");

            return new SoupComponent(id, componentId, DishId);
    }

    @Override
    public String mapToJson(List<SoupComponent> entities) {
        //TODO: not implemented yet
        return null;
    }

    @Override
    public String mapToJson(SoupComponent entity) {
        //TODO: not implemented yet
        return null;
    }
}
