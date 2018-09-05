package Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Mapper<E> {

    public E map(ResultSet resultSet) throws SQLException;
    public String mapToJson(List<E> entities);
    public String mapToJson(E entity);

}
