package DAO;

import Model.Soup;

import java.sql.SQLException;
import java.util.List;

public class SoupDAO extends AbstractDAO<Soup> {

    public SoupDAO() {
        ADD_QUERY = "INSERT INTO soup (name, favourite) VALUES (?, ?);";
        EDIT_QUERY = "UPDATE soup SET name=?, favourite=? WHERE soup_id=?;";
        DELETE_QUERY = "DELETE FROM soup WHERE soup_id=?;";
    }

    @Override
    void fillStatementToAddData(Soup entity) throws SQLException {
    }


    @Override
    void fillStatementToEditData(Soup entity) throws SQLException {
    }


    @Override
    void fillStatementToDeleteData(Soup entity) throws SQLException {
    }
}

