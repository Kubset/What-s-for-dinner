package DAO;

import Mappers.DishComponentMapper;
import Model.DishComponent;

import java.sql.SQLException;

public class DishComponentDAO extends AbstractDAO<DishComponent>{

    public DishComponentDAO() {
        ADD_QUERY = "INSERT INTO dish_components (dish_id, component_id, count, unit) VALUES (?, ?, ?, ?);";
        EDIT_QUERY = "UPDATE dish_components SET dish_id=?, component_id=?, count=?, unit=? WHERE dish_component_id=?;";
        DELETE_QUERY = "DELETE FROM dish_components WHERE dish_component_id=?;";
        super.mapper = new DishComponentMapper();
    }

    @Override
    void fillStatementToAddData(DishComponent entity) throws SQLException {
        super.preparedStatement.setInt(1, entity.getDishId());
        super.preparedStatement.setInt(2, entity.getComponentId());
        super.preparedStatement.setInt(3, entity.getCount());
        super.preparedStatement.setString(4, entity.getUnit().getName());
    }


    void fillStatementToEditData(DishComponent entity) throws SQLException {
        super.preparedStatement.setInt(1, entity.getDishId());
        super.preparedStatement.setInt(2,entity.getComponentId());
        super.preparedStatement.setInt(3,entity.getCount());
        super.preparedStatement.setString(4,entity.getUnit().getName());
        super.preparedStatement.setInt(5, entity.getId());
    }


    @Override
    void fillStatementToDeleteData(DishComponent entity) throws SQLException {
        super.preparedStatement.setInt(1, entity.getId());
    }
}
