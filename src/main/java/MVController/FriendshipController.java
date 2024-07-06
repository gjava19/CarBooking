package MVController;

import Models.Friend;
import Models.FriendInt;
import Models.User;
import db.DBConnector;
import db.DBFriendshipCommunicator;
import db.DBUserCommunicator;

import java.sql.SQLException;
import java.util.ArrayList;

public class FriendshipController {

    private DBFriendshipCommunicator fCommunicator;
    private DBUserCommunicator uCommunicator;

    /**
     * create user controller object
     * takes module which gives us info from table
     * @param connector object of connector
     */
    public FriendshipController(DBConnector connector) {
        this.fCommunicator = new DBFriendshipCommunicator(connector.getCon());
        this.uCommunicator = new DBUserCommunicator(connector.getCon());
    }

    /**
     * checks if user exist and fills user friends list
     * @param user information about user
     * @return true if could get list
     */
    public boolean getFriendsList(User user) throws SQLException {
        if( ! uCommunicator.checkUserExists(user.getUsername())) return false;
        ArrayList<String> friends = fCommunicator.getFriends(user.getId());
        for(String friend : friends) {

            int id = uCommunicator.getUserId(friend);
            if (id == -1) return false;

            user.addFriend(new Friend(friend, id));
        }
        return  true;
    }

    /**
     * checks if user exist and fills user send requests list
     * @param user information about user
     * @return true if could get list
     */
    public boolean getSentRequestList(User user) throws SQLException {
        if( ! uCommunicator.checkUserExists(user.getUsername())) return false;

        ArrayList<String> sent = fCommunicator.getSentRequest(user.getId());
        for(String friend : sent){

            int id =  uCommunicator.getUserId(friend);
            if(id == -1) return false;

            user.addSent(new Friend(friend, id));
        }
        return  true;
    }

    /**
     * checks if user exist and fills user received request list
     * @param user information about user
     * @return true if could get list
     */
    public boolean getReceivedRequestList(User user) throws SQLException {
        if( ! uCommunicator.checkUserExists(user.getUsername())) return false;

        ArrayList<String> received = fCommunicator.getReceivedRequest(user.getId());
        for(String candidate : received){

            int id =  uCommunicator.getUserId(candidate);
            if(id == -1) return false;

            user.addRequested(new Friend(candidate, id));
        }
        return  true;
    }

    public boolean fillUserRelations(User user) throws SQLException {
        boolean ret;
        ret = getFriendsList(user);         if(ret == false) return false;
        ret = getReceivedRequestList(user); if(ret == false) return false;
        ret = getSentRequestList(user);     if(ret == false) return false;
        return true;
    }

    /**
     * approve friendship
     *
     * @param user1 user which decide to approve
     * @param user2 user which sent request
     * @return true if approved
     * @throws SQLException
     */
    public boolean approveFriendship(User user1, User user2) throws SQLException {
        boolean status = fCommunicator.changeStatus(user1.getId(), user2.getId(), DBFriendshipCommunicator.FriendshipStatus.FRIENDSHIP_STATUS_FRIENDS);
        if( ! status) return false;
        user1.removeRequested(user2);
        user1.addFriend(user2);
        user2.removeSent(user1);
        user2.addFriend(user1);

        return true;
    }

    /**
     * reject friendship
     *
     * @param user1 user which rejected
     * @param user2 user which sent
     * @return true if rejected
     * @throws SQLException
     */
    public boolean rejectFriendship(User user1, User user2) throws SQLException {
        boolean status = fCommunicator.changeStatus(user1.getId(), user2.getId(), DBFriendshipCommunicator.FriendshipStatus.FRIENDSHIP_STATUS_STRANGERS);
        if(! status) return false;
        user1.removeFriend(user2);
        user1.removeRequested(user2);

        user2.removeFriend(user1);
        user2.removeSent(user1);

        return true;
    }

    /**
     * send friendship
     *
     * @param user1 user which sends
     * @param user2 user which receives
     * @return true if sent
     * @throws SQLException
     */
    public boolean sendFriendship(User user1, User user2) throws SQLException {
        boolean status = fCommunicator.createRequest(user1.getId(), user2.getId());
        if(! status) return false;
        user1.addRequested(user2);
        user2.addSent(user2);

        return true;
    }
}
