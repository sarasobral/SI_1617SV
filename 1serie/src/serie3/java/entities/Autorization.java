package serie3.java.entities;

import serie3.java.connectionToSql.SQLConnection;

import java.sql.SQLException;

/**
 * Created by Utilizador on 16/01/2017.
 */
public class Autorization
{
    User u;
    Permission p;
    Objecto o;
    public Autorization( User u, Permission p, Objecto o){
        this.u=u; this.p=p; this.o=o;
        try {
            PostAutorization.insertAutorization(SQLConnection.dataSourceConnection(), this);
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar à BD");
        }
    }
}
