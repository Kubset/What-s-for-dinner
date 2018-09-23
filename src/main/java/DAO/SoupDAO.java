package DAO;

import Mappers.SoupMapper;
import Model.Soup;

import java.sql.SQLException;
import java.util.List;

public class SoupDAO extends AbstractDAO<Soup> {

    public SoupDAO() {
        ADD_QUERY = "INSERT INTO soup (name, favourite, recipe) VALUES (?, ?, ?);";
        EDIT_QUERY = "UPDATE soup SET name=?, favourite=?, recipe=? WHERE soup_id=?;";
        DELETE_QUERY = "DELETE FROM soup WHERE soup_id=?;";
        super.mapper = new SoupMapper();
    }

    @Override
    void fillStatementToAddData(Soup entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getName());
        super.preparedStatement.setInt(2, entity.getFavourite());
        super.preparedStatement.setString(3, entity.getRecipe());
    }


    @Override
    void fillStatementToEditData(Soup entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getName());
        super.preparedStatement.setInt(2, entity.getFavourite());
        super.preparedStatement.setString(3, entity.getRecipe());
        super.preparedStatement.setInt(4,entity.getId());
    }


    @Override
    void fillStatementToDeleteData(Soup entity) throws SQLException {
        super.preparedStatement.setInt(1, entity.getId());
    }

}

