package Mappers;

import Model.SoupComponent;
import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SoupComponentMapper extends AbstractMapper<SoupComponent>{

    @Override
    public SoupComponent map(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("soup_component_id");
            int componentId = resultSet.getInt("component_id");
            int DishId = resultSet.getInt("soup_id");

            return new SoupComponent(id, componentId, DishId);
    }
}
