package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Woo on 2015-01-21.
 */
public class CountStatement implements StatementStrategy {
    @Override
    public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
        return null;
    }
}
