package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SoupComponentBySoupId implements SqlCriteria{
    private final String QUERY = "SELECT * FROM soup_components WHERE soup_id=?;";
    private int id;

    public SoupComponentBySoupId(int id) {
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
