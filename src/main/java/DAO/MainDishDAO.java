package DAO;

import Model.MainDish;

import java.sql.SQLException;
import java.util.List;

public class MainDishDAO extends AbstractDAO<MainDish> {

    public MainDishDAO() {
        ADD_QUERY = "INSERT INTO main_dish (name, favourite) VALUES (?, ?);";
        EDIT_QUERY = "UPDATE main_dish SET name=?, like=? WHERE dish_id=?;";
        DELETE_QUERY = "DELETE FROM main_dish WHERE dish_id=?;";
    }

    @Override
    void fillStatementToAddData(MainDish entity) throws SQLException {
    }


    @Override
    void fillStatementToEditData(MainDish entity) throws SQLException {
    }


    @Override
    void fillStatementToDeleteData(MainDish entity) throws SQLException {
    }

}
