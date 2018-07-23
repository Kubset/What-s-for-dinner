package Criteria;

import DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComponentsByName implements SqlCriteria {

    private final String QUERY = "SELECT * FROM components WHERE name=?;";
    private String name;

    public ComponentsByName(String name) {
        this.name = name;
    }

    @Override
    public PreparedStatement toPreparedStatement() throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionProvider.getConnection();
            preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, this.name);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Can't perform this query due to " +
                    "exception occurance when creating PreparedStatement");
        }

        return preparedStatement;
    }
}
