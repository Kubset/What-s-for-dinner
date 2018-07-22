package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<E> implements DAO<E> {

    String ADD_QUERY;
    String EDIT_QUERY;
    String DELETE_QUERY;
    String SELECT_QUERY;
    String SELECT_ALL;
    int EDIT_QUERY_KEY_INDEX;

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
            preparedStatement = dbConnection.prepareStatement(ADD_QUERY);
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
            preparedStatement = dbConnection.prepareStatement(ADD_QUERY);
            fillStatementToEditData(entity);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.err.println("Can't delete object from the database");
        }
    }

    @Override
    public E get(int ID) {
        return null;
    }

    @Override
    public List<E> get() {
        return null;
    }
}
