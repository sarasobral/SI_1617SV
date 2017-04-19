package serie3.java.connectionToSql.post;

import serie3.java.entities.Permission;
import serie3.java.entities.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class PostRole {

    public static void insertRole(Connection con, Role r) throws SQLException {
        try {
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into Role(r) values (?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, r.toString());
            pstmt.execute();

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
    }

    public static void addPermissionToRole(Connection con, Role r, Permission p) throws SQLException {
        try {
            con.setAutoCommit(false);
            PreparedStatement pstmt;

            pstmt = con.prepareStatement(
                    "insert into RolePermission(r, p) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, r.toString());
            pstmt.setString(2, p.toString());
            pstmt.execute();


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
    }

    public static void removePermissionToRole(Connection con, Role r, Permission p) throws SQLException {
        try {
            con.setAutoCommit(false);

            PreparedStatement pstm = con.prepareStatement("delete from RolePermission where r = ? and p = ?");
            pstm.setString(1, r.toString());
            pstm.setString(2, p.toString());
            pstm.execute();

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
    }
}
