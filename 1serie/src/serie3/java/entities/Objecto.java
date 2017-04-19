package serie3.java.entities;

import serie3.java.connectionToSql.SQLConnection;

import java.sql.SQLException;

/**
 * Created by Utilizador on 16/01/2017.
 */
public class Objecto {
    String object;
    public Objecto(String s){
        object=s;
        try {
            PostObject.insertObject(SQLConnection.dataSourceConnection(), this);
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar à BD");
        }
    }

    @Override
    public String toString() {
        return object;
    }
}
