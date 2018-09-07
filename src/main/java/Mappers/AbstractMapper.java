package Mappers;

import Model.Component;
import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractMapper<E> implements Mapper<E>{
    Gson gson;

    AbstractMapper() {
       this.gson = new Gson();
    }


    @Override
    public String mapToJson(List<E> entities) {
        return gson.toJson(entities).toString();
    }

    @Override
    public String mapToJson(E entity) {
        return gson.toJson(entity).toString();
    }

    public abstract E map(ResultSet resultSet) throws SQLException;
}
