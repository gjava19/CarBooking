package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBUserCommunicator;

public class DBFriendshipCommunicator {

    public enum FriendshipStatus{
        FRIENDSHIP_STATUS_STRANGERS,
        FRIENDSHIP_STATUS_REQUESTED,
        FRIENDSHIP_STATUS_RECEIVED,
        FRIENDSHIP_STATUS_FRIENDS,
        FRIENDSHIP_STATUS_END,
    };

    public static String friendshipStatusFriends = "friends";
    public static String friendshipStatusSent    = "sent";

    public static int COLUMNS_FRIENDSHIP_ID       = 0;
    public static int COLUMNS_FRIENDSHIP_ID1      = 1;
    public static int COLUMNS_FRIENDSHIP_ID2      = 2;
    public static int COLUMNS_FRIENDSHIP_STATUS   = 3;
    public static String[] columnFriendshipNames = {"id", "id1", "id2", "status"};

    static String friendshipTable       = "friendship";
    private Connection con;
    private DBUserCommunicator userCommunicator;


    public DBFriendshipCommunicator(Connection con) {
        this.con = con;
        userCommunicator = new DBUserCommunicator(con);
    }

    private ResultSet findRowFriendship(int id1, int id2) throws SQLException {
        String sql = "SELECT * FROM " + friendshipTable + " WHERE id1 = ? AND id2 = ?;";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1,    id1);
        ps.setInt(2,    id2);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    /**
     * checks status in freindshif table.
     * @param id1   first user unique id.
     * @param id2   second user unique id.
     * @return      one from the FRIENDSHIP_STATUS.
     */
    public FriendshipStatus getFriendshipstatus(int id1, int id2) throws SQLException {
        ResultSet result = findRowFriendship(id1, id2);

        String status= "";
        if (result.next()) {
            status = result.getString(COLUMNS_FRIENDSHIP_STATUS + 1);
            if (status.equals(friendshipStatusFriends)) return FriendshipStatus.FRIENDSHIP_STATUS_FRIENDS;
            if (status.equals(friendshipStatusSent))    return FriendshipStatus.FRIENDSHIP_STATUS_REQUESTED;
        }

        result = findRowFriendship(id2, id1);
        if(result.next()) {
            status = result.getString(COLUMNS_FRIENDSHIP_STATUS + 1);
            if (status.equals(friendshipStatusFriends)) return FriendshipStatus.FRIENDSHIP_STATUS_FRIENDS;
            if (status.equals(friendshipStatusSent))    return FriendshipStatus.FRIENDSHIP_STATUS_RECEIVED;
        }

        return FriendshipStatus.FRIENDSHIP_STATUS_STRANGERS;
    }

    /**
     * removes row from the sql table.
     * @param id1   first user unique id.
     * @param id2   second user unique id.
     * @return      true if row deleted.
     */
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

    /**
     * create row in sql table.
     * @param id1   first user unique id.
     * @param id2   second user unique id.
     * @return      true if row created.
     */
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

    /**
     * changes status in data table .
     * @param id1       first user unique id.
     * @param id2       second user unique id.
     * @param status    value from FriendshipStatus(req == sent, res == sent, friends == friend else return false)
     * @return          true if status changed.
     */
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

    /**
     * get list of friends usernames.
     *
     * @param id    user id.
     * @return      return list of friends usernames.
     */
    public ArrayList<String> getFriends(int id) throws SQLException {
        ArrayList<String> friends = new ArrayList<String>();
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

            friends.add(userCommunicator.getUsername(friendId));
        }

        return friends;
    }

    /**
     * get list of people who were requested to become freinds
     * by current user.
     *
     * @param id    user id.
     * @return      return list of people.
     */
    public ArrayList<String> getSentRequest(int id) throws SQLException {
        ArrayList<String> sentRequests = new ArrayList<String>();
        String sql = "SELECT id2 FROM " + friendshipTable + " WHERE id1 = ? AND status = ?;";

        return getStrings(id, sentRequests, sql, COLUMNS_FRIENDSHIP_ID2);
    }

    /**
     * get list of people which sent friend request to current id persone.
     *
     * @param id    user id.
     * @return      return list of people.
     */
    public ArrayList<String> getReceivedRequest(int id) throws SQLException {
        ArrayList<String> receivedRequests = new ArrayList<String>();
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

            Requests.add(userCommunicator.getUsername(temp));
        }

        return Requests;
    }
}
