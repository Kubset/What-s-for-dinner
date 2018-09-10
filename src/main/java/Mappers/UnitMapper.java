package Mappers;

import Model.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UnitMapper extends AbstractMapper<Component> {

    @Override
    public Component map(ResultSet resultSet) throws SQLException {
            String name = resultSet.getString("name");

            return new Unit(name);
    }
}
