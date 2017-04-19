package serie3.java.connectionToSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Utilizador on 15/01/2017.
 */
public class SQLConnection {

    public static Connection dataSourceConnection() {
        String url = "jdbc:sqlserver://localhost\\SQlExpress;integratedSecurity=true;databaseName=SegInf";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
