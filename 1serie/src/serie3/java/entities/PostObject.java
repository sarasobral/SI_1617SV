package serie3.java.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Utilizador on 16/01/2017.
 */
public class PostObject {
    public static void insertObject(Connection con, Objecto o) throws SQLException {
        try {
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into Object(o) values (?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, o.toString());
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
