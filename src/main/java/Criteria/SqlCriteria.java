package Criteria;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlCriteria {
    PreparedStatement toPreparedStatement() throws SQLException;
}

