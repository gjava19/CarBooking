package db;

import java.sql.*;
import java.util.ArrayList;

public class DBCommunicator implements UserLoginListener, FriendshipListener{

    /**
     * names of column indexes
     */
    public static int COLUMNS_USER_ID            = 0;
    public static int COLUMNS_USER_USERNAME      = 1;
    public static int COLUMNS_USER_PASSWORD      = 2;
    public static int COLUMNS_USER_SECRET_WORD   = 3;
    public static String[] columnUserNames = {"id", "username", "password", "secret_word"};

    public static int COLUMNS_FRIENDSHIP_ID       = 0;
    public static int COLUMNS_FRIENDSHIP_ID1      = 1;
    public static int COLUMNS_FRIENDSHIP_ID2      = 2;
    public static int COLUMNS_FRIENDSHIP_STATUS   = 3;
    public static String[] columnFriendshipNames = {"id", "id1", "id2", "status"};

    Connection con;
    private static ResultSet rs;
    private static String user                  = "root";
    private static String pass                  = "1235";
    private static String url                   = "jdbc:mysql://localhost:3303/quizwebdb";
    private static String userTable             = "user";
    private static String friendshipTable       = "friendship";

    /**
     * connect to database
     */
    public DBCommunicator() {
        try{
            con = DriverManager.getConnection(url, user, pass);
        }catch(SQLException e){e.printStackTrace();}
    }

    @Override
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

    @Override
    public boolean changePassword(String username, String password) throws SQLException{
        String sql = "UPDATE " + userTable + " SET password = ? WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, password); // set new password
        ps.setString(2, username);

        int affectedRows = ps.executeUpdate();
        return affectedRows > 0;
    }

    @Override
    public boolean checkUserexist(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + userTable + " WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        return rs.next() ? rs.getInt(1) > 0 : false;
    }

    @Override
    public boolean checkPassword(String username, String password) throws SQLException {
        String sql = "SELECT * FROM " + userTable + " WHERE username = ? AND password = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        return rs.next();
    }

    @Override
    public boolean checkSecretWord(String username, String secretWord) throws SQLException {
        String sql = "SELECT FROM " + userTable + " WHERE username = ? AND secret_secretword = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, secretWord);
        ResultSet rs = ps.executeQuery();

        return rs.next();
    }

    @Override
    public String getUsername(int id) throws SQLException {
        String sql = "SELECT username FROM " + userTable + " WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) return rs.getString(columnUserNames[COLUMNS_USER_USERNAME]);

        return "";
    }

    @Override
    public String getUserId(String username) throws SQLException {
        String sql = "SELECT id FROM user WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getString(columnUserNames[COLUMNS_USER_ID]);

        return "";
    }

    private ResultSet findRowFriendship(int id1, int id2) throws SQLException {
        String sql = "SELECT * FROM " + friendshipTable + " WHERE id1 = ? AND id2 = ?;";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1,    id1);
        ps.setInt(2,    id2);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    @Override
    public FriendshipStatus getFriendshipstatus(int id1, int id2) throws SQLException {
        ResultSet result = findRowFriendship(id1, id2);
        String status = result.getString(COLUMNS_FRIENDSHIP_STATUS + 1);
        if( ! status.equals("")) {
            if (status.equals(friendshipStatusFriends)) return FriendshipStatus.FRIENDSHIP_STATUS_FRIENDS;
            if (status.equals(friendshipStatusSent))    return FriendshipStatus.FRIENDSHIP_STATUS_REQUESTED;
        }

        result = findRowFriendship(id2, id1);
        status = result.getString(COLUMNS_FRIENDSHIP_STATUS + 1);
        if( ! status.equals("")) {
            if (status.equals(friendshipStatusFriends)) return FriendshipStatus.FRIENDSHIP_STATUS_FRIENDS;
            if (status.equals(friendshipStatusSent))    return FriendshipStatus.FRIENDSHIP_STATUS_RECEIVED;
        }

        return FriendshipStatus.FRIENDSHIP_STATUS_STRANGERS;
    }

    @Override
    public boolean makeStranger(int id1, int id2) throws SQLException {
        String sql = "DELETE FROM " + friendshipTable + " WHERE (id1 = ? AND id2 = ?) OR (id1 = ? AND id2 = ?);";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1,    id1);
        ps.setInt(2,    id2);
        ps.setInt(1,    id2);
        ps.setInt(2,    id1);
        int rowsDeleted = ps.executeUpdate();

        return rowsDeleted > 0;
    }

    private boolean checkRequestExist(int id1, int id2) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM " + friendshipTable + " WHERE (id1 = ? AND id2 = ?) OR (id1 = ? AND id2 = ?);";
        PreparedStatement ps = con.prepareStatement(checkSql);
        ps.setInt(1, id1);
        ps.setInt(2, id2);
        ps.setInt(3, id2);
        ps.setInt(4, id1);
        ResultSet rs = ps.executeQuery();

        rs.next();

        return rs.getInt(1) > 0;
    }

    @Override
    public boolean createRequest(int id1, int id2) throws SQLException {
        if(checkRequestExist(id1, id2)) return false;
        String sql = "INSERT INTO " + friendshipTable + " (id1, id2, status) VALUES (?, ?, ?);";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,    id1);
        ps.setInt(2,    id2);
        ps.setString(3, friendshipStatusSent);
        int rowsInserted = ps.executeUpdate();

        return rowsInserted > 0;
    }

    @Override
    public boolean changeStatus(int id1, int id2, FriendshipStatus status) throws SQLException {
        String sql = "UPDATE " + friendshipTable + " SET status = ? WHERE (id1 = ? AND id2 = ?) OR (id1 = ? AND id2 = ?);";

        PreparedStatement ps = con.prepareStatement(sql);
        if(status == FriendshipStatus.FRIENDSHIP_STATUS_FRIENDS) ps.setString(1, friendshipStatusFriends);
        else                                                     ps.setString(1, friendshipStatusSent);
        ps.setInt(2, id1);
        ps.setInt(3, id2);
        ps.setInt(4, id2);
        ps.setInt(5, id1);

        int rowsUpdated = ps.executeUpdate();

        return rowsUpdated > 0;
    }

    @Override
    public ArrayList<String> getFriends(int id) throws SQLException {
        ArrayList<String> friends = new ArrayList<>();
        String sql = "SELECT id1, id2 FROM " + friendshipTable + " WHERE (id1 = ? OR id2 = ?) AND status = ?;";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setInt(2, id);
        ps.setString(3, friendshipStatusFriends);

        ResultSet rs = ps.executeQuery();
        int friendId1, friendId2, friendId;
        while (rs.next()) {
            friendId1 = rs.getInt(columnFriendshipNames[COLUMNS_FRIENDSHIP_ID1]);
            friendId2 = rs.getInt(columnFriendshipNames[COLUMNS_FRIENDSHIP_ID2]);

            friendId = (friendId1 == id) ? friendId2 : friendId1;

            friends.add(getUsername(friendId));
        }

        return friends;
    }

    @Override
    public ArrayList<String> getSentRequest(int id) throws SQLException {
        ArrayList<String> sentRequests = new ArrayList<>();
        String sql = "SELECT id2 FROM " + friendshipTable + " WHERE id1 = ? AND status = ?;";

        return getStrings(id, sentRequests, sql, COLUMNS_FRIENDSHIP_ID2);
    }

    @Override
    public ArrayList<String> getReceivedRequest(int id) throws SQLException {
        ArrayList<String> receivedRequests = new ArrayList<>();
        String sql = "SELECT id1 FROM " + friendshipTable + " WHERE id2 = ? AND status = ?;";

        return getStrings(id, receivedRequests, sql, COLUMNS_FRIENDSHIP_ID1);
    }

    private ArrayList<String> getStrings(int id, ArrayList<String> Requests, String sql, int columnsFriendshipId) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, friendshipStatusSent);

        ResultSet rs = ps.executeQuery();
        int temp;
        while (rs.next()) {
            temp = rs.getInt(columnFriendshipNames[columnsFriendshipId]);

            Requests.add(getUsername(temp));
        }

        return Requests;
    }
}
