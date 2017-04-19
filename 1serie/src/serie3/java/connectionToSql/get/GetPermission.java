package serie3.java.connectionToSql.get;

import serie3.java.entities.Permission;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Utilizador on 15/01/2017.
 */
public class GetPermission {
    public List<Permission> selectPermission(Connection con) throws SQLException {
        List<Permission> permissions = new LinkedList<>();
        try {
            con.setAutoCommit(false);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select p from Permission");
            while (rs.next()) {
                permissions.add(new Permission(rs.getString("p")));
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
        return permissions;
    }
    public Permission selectPermission(Connection con, String p) throws SQLException {
        Permission permission = null;
        try {
            con.setAutoCommit(false);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select p from Permission where p = "+p);
            while (rs.next()) {
                permission = (new Permission(rs.getString("p")));
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
        return permission;
    }
}
