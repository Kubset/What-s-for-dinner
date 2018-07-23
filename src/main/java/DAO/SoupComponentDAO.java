package DAO;

import java.sql.SQLException;

public class SoupComponentDAO extends AbstractDAO<SoupComponent>{

    public SoupComponentDAO() {
        ADD_QUERY = "INSERT INTO soup_components (soup_id, component_id) VALUES (?, ?);";
        EDIT_QUERY = "UPDATE soup_components SET soup_id=?, component_id=? WHERE soup_component_id=?;";
        DELETE_QUERY = "DELETE FROM soup_components WHERE soup_component_id=?;";
        super.mapper = new SoupComponentMapper();
    }

    @Override
    void fillStatementToAddData(SoupComponent entity) throws SQLException {
        super.preparedStatement.setInt(1, entity.getSoupId());
        super.preparedStatement.setInt(2, entity.getComponentId());
    }


    void fillStatementToEditData(SoupComponent entity) throws SQLException {
        super.preparedStatement.setInt(1, entity.getSoupId());
        super.preparedStatement.setInt(2,entity.getComponentId());
        super.preparedStatement.setInt(3, entity.getId());
    }


    @Override
    void fillStatementToDeleteData(SoupComponent entity) throws SQLException {
        super.preparedStatement.setInt(1, entity.getId());
    }
}
