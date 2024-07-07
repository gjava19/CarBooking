package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {

    private Connection con;
    private static String user  = "root";
    private static String pass  = "1234";
    private static String url   = "jdbc:mysql://localhost:3306/quizwebdb";

    /**
     * connect to database
     */
    public DBConnector() {
        this(url);
    }

    public DBConnector(String url) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException e) {e.printStackTrace();}
        catch (SQLException e) {e.printStackTrace();}
    }

    public Connection getCon() {
        return con;
    }

}
