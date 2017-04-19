package serie3.java.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Utilizador on 16/01/2017.
 */
public class PostAutorization {
    public static void insertAutorization(Connection con, Autorization autorization) throws SQLException {
        try {
            //obter a role
            //obter as permissoes desse role
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into Autorization(u,p,o) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, autorization.u.toString());
            pstmt.setString(2, autorization.p.toString());
            pstmt.setString(3, autorization.o.toString());
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
