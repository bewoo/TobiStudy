package springbook.user.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Woo on 2015-01-21.
 */
public class JdbcContext {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();
            pstmt = stmt.makePreparedStatement(conn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (pstmt!=null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
            if (conn!=null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
        }
    }

    public void executeSql(final String query) throws SQLException {
        workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
                PreparedStatement pstmt = conn.prepareStatement(query);
                return pstmt;
            }
        });
    }

    public void executeSql(final String query, final String... args) throws SQLException {
        workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
                PreparedStatement pstmt = conn.prepareStatement(query);
                int i = 1;
                for (String arg : args) {
                    pstmt.setString(i, arg);
                    i++;
                }
                return pstmt;
            }
        });
    }
}
