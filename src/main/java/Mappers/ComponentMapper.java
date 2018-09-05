package Mappers;

import Model.Component;
import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ComponentMapper implements Mapper<Component> {

    @Override
    public Component map(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("component_id");
            String name = resultSet.getString("name");

            return new Component(id, name);
    }

    @Override
    public String mapToJson(List<Component> components) {
        Gson gson = new Gson();
        return gson.toJson(components).toString();

    }

    @Override
    public String mapToJson(Component component) {
        Gson gson = new Gson();
        return gson.toJson(component).toString();

    }
}
