package serie3.java.connectionToSql.post;

import serie3.java.entities.Role;
import serie3.java.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class PostUser {

    public static void insertUser(Connection con, User u) throws SQLException {
        try {
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into UserName(u) values (?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, u.toString());
            pstmt.execute();

            con.setAutoCommit(true);
        }catch(SQLException e){
            con.rollback();
            System.out.println(e.getMessage());
        }finally {
            if(!con.isClosed()){
                con.commit();
                con.close();
            }
        }
    }
    public static void addRoleToUser(Connection con, User u, Role r) throws SQLException {
        try {
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into UserRole(u, r) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, u.toString());
            pstmt.setString(2, r.toString());
            pstmt.execute();

            con.setAutoCommit(true);
        }catch(SQLException e){
            con.rollback();
            System.out.println(e.getMessage());
        }finally {
            if(!con.isClosed()){
                con.commit();
                con.close();
            }
        }
    }
    public static void removeRoleToUser(Connection con, User u, Role r) throws SQLException {
        try {
            con.setAutoCommit(false);

            PreparedStatement pstm = con.prepareStatement("delete from UserRole where u = ? and r = ?");
            pstm.setString(1, u.toString());
            pstm.setString(2, r.toString());
            pstm.execute();

            con.setAutoCommit(true);
        }catch(SQLException e){
            con.rollback();
            System.out.println(e.getMessage());
        }finally {
            if(!con.isClosed()){
                con.commit();
                con.close();
            }
        }
    }


}
