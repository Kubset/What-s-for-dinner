package DAO;

import Mappers.ComponentMapper;
import Model.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ComponentDAO extends AbstractDAO<Component> {

    public ComponentDAO() {
        ADD_QUERY = "INSERT INTO components (name) VALUES (?);";
        EDIT_QUERY = "UPDATE components SET name=? WHERE component_id=?;";
        DELETE_QUERY = "DELETE FROM components WHERE component_id=?;";
        super.mapper = new ComponentMapper();
    }

    @Override
    void fillStatementToAddData(Component entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getName());
    }


    @Override
    void fillStatementToEditData(Component entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getName());
        super.preparedStatement.setInt(2,entity.getId());
    }


    @Override
    void fillStatementToDeleteData(Component entity) throws SQLException {
        super.preparedStatement.setInt(1, entity.getId());
    }

}
