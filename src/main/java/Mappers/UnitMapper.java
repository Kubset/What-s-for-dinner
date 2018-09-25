package Mappers;

import Model.Component;
import Model.Unit;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UnitMapper extends AbstractMapper<Unit> {

    @Override
    public Unit map(ResultSet resultSet) throws SQLException {
            String name = resultSet.getString("name");
            int id = resultSet.getInt("unit_id");

            return new Unit(name, id);
    }
}
