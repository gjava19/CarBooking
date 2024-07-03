package db;

import java.sql.*;

public class DBUserCommunicator {
    /**
     * names of column indexes
     */
    public static int COLUMNS_USER_ID            = 0;
    public static int COLUMNS_USER_USERNAME      = 1;
    public static int COLUMNS_USER_PASSWORD      = 2;
    public static int COLUMNS_USER_SECRET_WORD   = 3;
    public static String[] columnUserNames = {"id", "username", "password", "secret_word"};

    private static String userTable             = "user";
    Connection con;

    public DBUserCommunicator(Connection con) {
        this.con = con;
    }

    public boolean createUser(String username, String password, String secretWord) throws SQLException {
        if(checkUserexist(username)) {
            System.out.println("User already exists");
            return false;
        }
        String sql = "INSERT INTO " + userTable + " (username, password, secret_word) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, secretWord);

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) throw new SQLException("Creating user failed, no rows affected.");

        ResultSet generatedKeys = ps.getGeneratedKeys();
        if ( ! generatedKeys.next()) throw new SQLException("Creating user failed, no ID obtained.");
        return true;
    }

    public boolean changePassword(String username, String password) throws SQLException{
        String sql = "UPDATE " + userTable + " SET password = ? WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, password); // set new password
        ps.setString(2, username);

        int affectedRows = ps.executeUpdate();
        return affectedRows > 0;
    }

    public boolean checkUserexist(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + userTable + " WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        return rs.next() ? rs.getInt(1) > 0 : false;
    }

    public boolean checkPassword(String username, String password) throws SQLException {
        String sql = "SELECT * FROM " + userTable + " WHERE username = ? AND password = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        return rs.next();
    }

    public boolean checkSecretWord(String username, String secretWord) throws SQLException {
        String sql = "SELECT FROM " + userTable + " WHERE username = ? AND secret_secretword = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, secretWord);
        ResultSet rs = ps.executeQuery();

        return rs.next();
    }

    public String getUsername(int id) throws SQLException {
        String sql = "SELECT username FROM " + userTable + " WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) return rs.getString(columnUserNames[COLUMNS_USER_USERNAME]);

        return "";
    }

    public String getUserId(String username) throws SQLException {
        String sql = "SELECT id FROM user WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getString(columnUserNames[COLUMNS_USER_ID]);

        return "";
    }

}
