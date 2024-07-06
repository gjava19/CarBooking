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

    /**
     * save new user information in database.
     *
     * @param username   client username (unique).
     * @param password   client password (save encrypted).
     * @param secretWord client answer to question (for restore password).
     * @return           true if user created
     */
    public boolean createUser(String username, String password, String secretWord) throws SQLException {
        if(checkUserExists(username)) {
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


      /**
     * check if username and password match (use while login)
     * @param username  current client username
     * @param password  current client password
     * @return          true if they match
     */
    public boolean deleteUser(String username) throws SQLException {
        if (!checkUserExists(username))
            return false;

        String sql = "delete from " + userTable + " where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, getUserId(username));

        int rowsDeleted = ps.executeUpdate();

        return rowsDeleted == 1;
    }


    /**
     * change user password when restore it.
     *
     * @param username  username.
     * @param password  new password.
     * @return          true if password changed
     */

    public boolean changePassword(String username, String password) throws SQLException{
        String sql = "UPDATE " + userTable + " SET password = ? WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, password); // set new password
        ps.setString(2, username);

        int affectedRows = ps.executeUpdate();
        return affectedRows > 0;
    }


    /**
     * search if user exist.
     *
     * @param username search name.
     * @return error code, 0 if all good and user dont exist. userExisit if user exist.
     */
    public boolean checkUserExists(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + userTable + " WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        return rs.next() ? rs.getInt(1) > 0 : false;
    }

    /**
     * check if username and password match (use while login)
     * @param username  current client username
     * @param password  current client password
     * @return          true if they match
     */
    public boolean checkPassword(String username, String password) throws SQLException {
        String sql = "SELECT * FROM " + userTable + " WHERE username = ? AND password = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        return rs.next();
    }

    /**
     * check if username and secretWord match (use while restroe password)
     * @param username      current client username
     * @param secretWord    current client secret word
     * @return              true if they match
     */
    public boolean checkSecretWord(String username, String secretWord) throws SQLException {
        String sql = "SELECT * FROM " + userTable + " WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        String actualSecretWord = "";
        if(rs.next())  actualSecretWord = rs.getString(columnUserNames[COLUMNS_USER_SECRET_WORD]);
        return actualSecretWord.equals(secretWord);
    }

    /**
     * by user id returns username (helps to connect two data table to each-other)
     * @param id    current username id
     * @return      username or empty string
     */
    public String getUsername(int id) throws SQLException {
        String sql = "SELECT username FROM " + userTable + " WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) return rs.getString(columnUserNames[COLUMNS_USER_USERNAME]);

        return "";
    }

    /**
     * by user username returns id (helps to connect two data table to each-other)
     * @param username  current username
     * @return          id or -1
     */
    public int getUserId(String username) throws SQLException {
        String sql = "SELECT id FROM user WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt(columnUserNames[COLUMNS_USER_ID]);

        return -1;
    }

}