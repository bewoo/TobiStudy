package springbook.user.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao {

    private DataSource dataSource;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {
        Connection conn = dataSource.getConnection();
        String sql = "INSERT INTO USERS"+
                          "(id, name, password)"+
                      "VALUES"+
                          "(?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.execute();

        pstmt.close();
        conn.close();
    }

    public User get(String id) throws SQLException {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        rs.close();
        pstmt.close();
        conn.close();
        if (user==null) {
            throw new EmptyResultDataAccessException(1);
        }
        return user;
    }

    public void deleteAll() throws SQLException {
        Connection conn = dataSource.getConnection();
        String sql = "delete from users";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    public int getCount() throws  SQLException {
        Connection conn = dataSource.getConnection();
        String sql = "select count(*) from users";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        pstmt.close();
        conn.close();

        return count;
    }
}
