package DAO;

import Mappers.ComponentMapper;
import Mappers.UnitMapper;
import Model.Component;
import Model.Unit;

import java.sql.SQLException;

public class UnitDAO extends AbstractDAO<Unit> {

    public UnitDAO() {
        ADD_QUERY = "INSERT INTO units (name) VALUES (?);";
        EDIT_QUERY = "UPDATE units SET name=? WHERE unit_id=?;";
        DELETE_QUERY = "DELETE FROM units WHERE name=?;";
        super.mapper = new UnitMapper();
    }

    @Override
    void fillStatementToAddData(Unit entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getName());
    }


    @Override
    void fillStatementToEditData(Unit entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getName());
        super.preparedStatement.setInt(2,entity.getId());
    }


    @Override
    void fillStatementToDeleteData(Unit entity) throws SQLException {
        super.preparedStatement.setInt(1, entity.getId());
    }

}
