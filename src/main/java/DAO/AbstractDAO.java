package DAO;

import Criteria.SqlCriteria;
import Mappers.Mapper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<E> implements DAO<E> {

    String ADD_QUERY;
    String EDIT_QUERY;
    String DELETE_QUERY;
    String SELECT_QUERY;
    String SELECT_ALL;
    int EDIT_QUERY_KEY_INDEX;
    Mapper<E> mapper;

    Connection dbConnection;
    PreparedStatement preparedStatement;

    abstract void fillStatementToAddData (E entity) throws SQLException;
    abstract void fillStatementToEditData (E entity) throws SQLException;
    abstract void fillStatementToDeleteData (E entity) throws SQLException;

    @Override
    public void add(E entity) {
        try {
            dbConnection = ConnectionProvider.getConnection();
            preparedStatement = dbConnection.prepareStatement(ADD_QUERY);
            fillStatementToAddData(entity);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.err.println("Can't add object to the database");
        }

    }

    @Override
    public void update(E entity) {
        try {
            dbConnection = ConnectionProvider.getConnection();
            preparedStatement = dbConnection.prepareStatement(EDIT_QUERY);
            fillStatementToEditData(entity);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.err.println("Can't edit object in the database");
        }
    }

    @Override
    public void delete(E entity) {
        try {
            dbConnection = ConnectionProvider.getConnection();
            preparedStatement = dbConnection.prepareStatement(DELETE_QUERY);
            fillStatementToDeleteData(entity);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            System.err.println("Can't delete object from the database");
        }
    }

    @Override
    public List<E> get(SqlCriteria sqlCriteria) {
        List<E> entities = new ArrayList<>();

        try {
            ResultSet resultSet = sqlCriteria.toPreparedStatement().executeQuery();
            while (resultSet.next()) {
                entities.add(mapper.map(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Cannot perform this query");
        }
        return entities;
    }
}
