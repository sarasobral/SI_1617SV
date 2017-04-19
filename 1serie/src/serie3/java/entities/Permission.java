package serie3.java.entities;

import serie3.java.connectionToSql.post.PostPermission;
import serie3.java.connectionToSql.SQLConnection;

import java.sql.SQLException;

/**
 * Created by Utilizador on 15/01/2017.
 */
public class Permission{
    String p;
    public Permission(String p){
        this.p=p;
        try {
            PostPermission.insertPermision(SQLConnection.dataSourceConnection(), this);
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar à BD");
        }
    }

    @Override
    public String toString() {
        return p;
    }
}