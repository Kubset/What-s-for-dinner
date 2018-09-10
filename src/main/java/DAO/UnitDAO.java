package DAO;

import Mappers.ComponentMapper;
import Model.Component;

import java.sql.SQLException;

public class UnitDAO extends AbstractDAO<Component> {

    public UnitDAO() {
        ADD_QUERY = "INSERT INTO units (name) VALUES (?);";
        EDIT_QUERY = "UPDATE units SET name=? WHERE name=?;";
        DELETE_QUERY = "DELETE FROM units WHERE name=?;";
        super.mapper = new UnitMapper();
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
