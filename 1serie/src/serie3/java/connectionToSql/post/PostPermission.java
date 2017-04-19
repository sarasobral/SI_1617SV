package serie3.java.connectionToSql.post;

import serie3.java.entities.Permission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class PostPermission {

    public static void insertPermision(Connection con, Permission p) throws SQLException {
        try {
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into Permission(p) values (?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, p.toString());
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


}
