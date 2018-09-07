package Mappers;

import Model.Soup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SoupMapper extends AbstractMapper<Soup> {

    @Override
    public Soup map(ResultSet resultSet) throws SQLException {
            int id = resultSet.getInt("soup_id");
            int favourite = resultSet.getInt("favourite");
            String name = resultSet.getString("name");

            return new Soup(id, name, favourite);
    }
}
