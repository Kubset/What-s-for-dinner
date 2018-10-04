package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class GetById implements SqlCriteria {

    private final String QUERY;
    private int id;

    public GetById(String QUERY, int id) {
        this.QUERY = QUERY;
        this.id = id;
    }

    @Override
    public PreparedStatement toPreparedStatement() throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, this.id);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Can't perform this query due to " +
                    "exception occurance when creating PreparedStatement");
        }

        return preparedStatement;
    }
}
