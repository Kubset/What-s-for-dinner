package Mappers;

import Model.SoupComponent;
import Model.Unit;
import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SoupComponentMapper extends AbstractMapper<SoupComponent>{

    @Override
    public SoupComponent map(ResultSet resultSet) throws SQLException {
            int componentId = resultSet.getInt("component_id");
            int soupId = resultSet.getInt("soup_id");
            int count = resultSet.getInt("count");
            String unit = resultSet.getString("unit");

            return new SoupComponent(soupId, componentId, count, new Unit(unit));
    }
}
