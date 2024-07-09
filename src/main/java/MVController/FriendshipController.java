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
        user.removeAllFriend(user);
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
        user.removeAllSent(user);
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
        user.removeAllRequested(user);
        for(String candidate : received){

            int id =  uCommunicator.getUserId(candidate);
            if(id == -1) return false;

            user.addRequested(new Friend(candidate, id));
        }
        return  true;
    }

    public boolean fillUserRelations(User user) throws SQLException {
        boolean ret;
        ret = getFriendsList(user);
        if(!ret) return false;
        ret = getReceivedRequestList(user);
        if(!ret) return false;
        ret = getSentRequestList(user);
        if(!ret) return false;
        return true;
    }

    /**
     * approve friendship
     *
     * @param user1Id user which decide to approve
     * @param user2Id user which sent request
     * @return true if approved
     * @throws SQLException
     */
    public boolean approveFriendship(int user1Id, int user2Id) throws SQLException {
        boolean status = fCommunicator.changeStatus(user1Id,user2Id, DBFriendshipCommunicator.FriendshipStatus.FRIENDSHIP_STATUS_FRIENDS);
        if( ! status) return false;
        return true;
    }

    /**
     * reject friendship
     *
     * @param user1Id user which rejected
     * @param user2Id user which sent
     * @return true if rejected
     * @throws SQLException
     */
    public boolean rejectFriendship(int user1Id, int user2Id) throws SQLException {
        boolean status = fCommunicator.makeStranger(user1Id, user2Id);
        if(! status) return false;
        return true;
    }

    /**
     * send friendship
     *
     * @param user1Id user which sends
     * @param user2Id user which receives
     * @return true if sent
     * @throws SQLException
     */
    public boolean sendFriendship( int user1Id, int user2Id) throws SQLException {
        boolean status = fCommunicator.createRequest(user1Id, user2Id);
        if(!status) return false;
        return true;
    }
}
