package serie3.java.connectionToSql.get;

import serie3.java.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Utilizador on 15/01/2017.
 */
public class GetUser {
    public List<User> selectUser(Connection con) throws SQLException {
        List<User> users = new LinkedList<>();
        try {
            con.setAutoCommit(false);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select u from User");
            while (rs.next()) {
                users.add(new User(rs.getString("u")));
            }
            con.setAutoCommit(true);
        } catch (SQLException e) {
            con.rollback();
            System.out.println(e.getMessage());
        } finally {
            if (!con.isClosed()) {
                con.commit();
                con.close();
            }
        }
        return users;
    }
    public User selectUser(Connection con, String u) throws SQLException {
        User user = null;
        try {
            con.setAutoCommit(false);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select u from User where u = "+u);
            while (rs.next()) {
                user = (new User(rs.getString("u")));
            }
            con.setAutoCommit(true);
        } catch (SQLException e) {
            con.rollback();
            System.out.println(e.getMessage());
        } finally {
            if (!con.isClosed()) {
                con.commit();
                con.close();
            }
        }
        return user;
    }
}
