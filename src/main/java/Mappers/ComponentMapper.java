package Mappers;

import Model.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponentMapper implements Mapper<Component> {

    public Component map(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("component_id");
            String name = resultSet.getString("name");

            return new Component(id, name);
    }
}
