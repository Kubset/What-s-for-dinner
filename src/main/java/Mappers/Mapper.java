package Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<E> {

    public E map(ResultSet resultSet) throws SQLException;

}
