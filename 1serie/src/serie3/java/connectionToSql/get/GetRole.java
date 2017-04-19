package serie3.java.connectionToSql.get;

import serie3.java.entities.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Utilizador on 15/01/2017.
 */
public class GetRole {
    public List<Role> selectRole(Connection con) throws SQLException {
        List<Role> roles = new LinkedList<>();
        try {
            con.setAutoCommit(false);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select r from Role");
            while (rs.next()) {
                roles.add(new Role(rs.getString("r")));
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
        return roles;
    }
    public Role selectPermission(Connection con, String r) throws SQLException {
        Role role = null;
        try {
            con.setAutoCommit(false);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select r from Role where r = "+r);
            while (rs.next()) {
                role = (new Role(rs.getString("r")));
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
        return role;
    }
}
