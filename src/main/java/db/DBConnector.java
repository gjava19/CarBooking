package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {

    Connection con;
    private static String user  = "root";
    private static String pass  = "password";
    private static String url   = "jdbc:mysql://localhost:3306/quizwebdb";

    /**
     * connect to database
     */
    public DBConnector() {
        this(url);
    }

    public DBConnector(String url) {
        try{ con = DriverManager.getConnection(url, user, pass);}
        catch(SQLException e){e.printStackTrace();}
    }

    public Connection getCon() {
        return con;
    }

}
