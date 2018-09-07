package Mappers;

import Model.Component;
import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ComponentMapper extends AbstractMapper<Component> {

    @Override
    public Component map(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("component_id");
            String name = resultSet.getString("name");

            return new Component(id, name);
    }
}
