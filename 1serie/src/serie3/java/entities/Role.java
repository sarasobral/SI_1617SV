package serie3.java.entities;

import serie3.java.connectionToSql.post.PostRole;
import serie3.java.connectionToSql.SQLConnection;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Utilizador on 15/01/2017.
 */
public class Role{
    private String r;
    private List<Permission> permissions = new LinkedList<Permission>();
    public Role(String r){
        this.r=r;
        try {
            PostRole.insertRole(SQLConnection.dataSourceConnection(), this);
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar à BD");
        }
    }

    public void addPermission(Permission p){
        permissions.add(p);
        try {
            PostRole.addPermissionToRole(SQLConnection.dataSourceConnection(), this, p);
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar à BD");
        }
    }

    public void removePermission(Permission p){
        permissions.remove(p);
        try {
            PostRole.removePermissionToRole(SQLConnection.dataSourceConnection(), this, p);
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar à BD");
        }
    }

    public List<Permission> getPermissions(){
        return permissions;
    }

    @Override
    public String toString() {
        return r;
    }
}