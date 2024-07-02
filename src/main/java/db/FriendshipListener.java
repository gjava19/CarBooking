package db;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FriendshipListener {
    enum FriendshipStatus{
        FRIENDSHIP_STATUS_STRANGERS,
        FRIENDSHIP_STATUS_REQUESTED,
        FRIENDSHIP_STATUS_RECEIVED,
        FRIENDSHIP_STATUS_FRIENDS,
        FRIENDSHIP_STATUS_END,
    };

    public static String friendshipStatusFriends = "friends";
    public static String friendshipStatusSent    = "sent";


    /**
     * checks status in freindshif table.
     * @param id1   first user unique id.
     * @param id2   second user unique id.
     * @return      one from the FRIENDSHIP_STATUS.
     */
    FriendshipStatus getFriendshipstatus(int id1, int id2) throws SQLException;

    /**
     * removes row from the sql table.
     * @param id1   first user unique id.
     * @param id2   second user unique id.
     * @return      true if row deleted.
     */
    boolean makeStranger(int id1, int id2) throws SQLException;

    /**
     * create row in sql table.
     * @param id1   first user unique id.
     * @param id2   second user unique id.
     * @return      true if row created.
     */
    boolean createRequest(int id1, int id2) throws SQLException;

    /**
     * changes status in data table .
     * @param id1       first user unique id.
     * @param id2       second user unique id.
     * @param status    value from FriendshipStatus(req == sent, res == sent, friends == friend else return false)
     * @return          true if status changed.
     */
    boolean changeStatus(int id1, int id2, FriendshipStatus status) throws SQLException;

    /**
     * get list of friends usernames.
     *
     * @param id    user id.
     * @return      return list of friends usernames.
     */
    ArrayList<String> getFriends(int id) throws SQLException;

    /**
     * get list of people who were requested to become freinds
     * by current user.
     *
     * @param id    user id.
     * @return      return list of people.
     */
    ArrayList<String> getSentRequest(int id) throws SQLException;

    /**
     * get list of people which sent friend request to current id persone.
     *
     * @param id    user id.
     * @return      return list of people.
     */
    ArrayList<String> getReceivedRequest(int id) throws SQLException;

}
