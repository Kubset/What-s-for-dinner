package Mappers;

import Model.Soup;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SoupMapper implements Mapper<Soup> {

    public Soup map(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("component_id");
            int favourite = resultSet.getInt("favourite");
            String name = resultSet.getString("name");

            return new Soup(id, name, favourite);
    }
}
