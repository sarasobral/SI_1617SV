package serie3.java.entities;

import serie3.java.connectionToSql.post.PostUser;
import serie3.java.connectionToSql.SQLConnection;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Utilizador on 15/01/2017.
 */
public class User{
    private String u;
    private List<Role> userRole = new LinkedList<>();
    public User(String u){ this.u=u;
        try {
            PostUser.insertUser(SQLConnection.dataSourceConnection(), this);
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar à BD");
        }
    }

    public void addRole(Role r){
        userRole.add(r);
        try {
            PostUser.addRoleToUser(SQLConnection.dataSourceConnection(), this, r);
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar à BD");
        }
    }
    public void removeRole(Role r){
        userRole.remove(r);
        try {
            PostUser.removeRoleToUser(SQLConnection.dataSourceConnection(), this, r);
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar à BD");
        }
    }

    public List<Role> getUserRole(){
        return userRole;
    }

    @Override
    public String toString() {
        return u;
    }
}