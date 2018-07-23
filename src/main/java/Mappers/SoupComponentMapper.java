package Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SoupComponentMapper implements Mapper<SoupComponent>{

    public SoupComponent map(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("soup_component_id");
            int componentId = resultSet.getInt("component_id");
            int DishId = resultSet.getInt("soup_id");

            return new SoupComponent(id, componentId, DishId);
    }
}
